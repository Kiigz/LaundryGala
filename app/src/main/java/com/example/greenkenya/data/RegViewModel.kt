package com.example.greenkenya.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.greenkenya.models.Registration
import com.example.greenkenya.navigation.ROUT_LOGIN
import com.example.greenkenya.navigation.ROUT_REGISTER
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class RegViewModel(var navController: NavController, var context: Context) {
    var authViewModel:AuthViewModel
    var progress: ProgressDialog
    init {
        authViewModel = AuthViewModel(navController, context)
        if (!authViewModel.isLoggedIn()){
            navController.navigate(ROUT_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun uploadRegistration(name:String, quantity:String, price:String, phone: String,filePath: Uri){
        val registrationId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference()
            .child("Registrations/$registrationId")
        progress.show()
        storageRef.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storageRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var registration = Registration(name,quantity,price,phone,imageUrl,registrationId )
                    var databaseRef = FirebaseDatabase.getInstance().getReference()
                        .child("registrations/$registrationId")
                    databaseRef.setValue(registration).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                Toast.makeText(this.context, "Upload error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun allRegistrations(
        registration: MutableState<Registration>,
        registrations: SnapshotStateList<Registration>): SnapshotStateList<Registration> {
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Registrations")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                registrations.clear()
                for (snap in snapshot.children){
                    var retrievedRegistration = snap.getValue(Registration::class.java)
                    registration.value = retrievedRegistration!!
                    registrations.add(retrievedRegistration)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return registrations
    }

    fun updateRegistration(registrationId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Registration/$registrationId")
        ref.removeValue()
        navController.navigate(ROUT_REGISTER)
    }


    fun deleteRegistration(registrationId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Registration/$registrationId")
        ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }



}