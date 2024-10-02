package com.example.greenkenya.ui.theme.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.greenkenya.R
import com.example.greenkenya.data.AuthViewModel
import com.example.greenkenya.navigation.ROUT_LOGIN
import com.example.greenkenya.navigation.ROUT_SIGNUP
import com.example.greenkenya.ui.theme.Blue
import com.example.greenkenya.ui.theme.DarkGreen
import com.example.greenkenya.ui.theme.Green


@Composable

fun SignupScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.greentriangle), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.recycleicon),
            contentDescription ="home",
            modifier = Modifier
                .size(150.dp),
            contentScale = ContentScale.Crop

        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "LaundryGala",
            fontSize = 60.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Magenta

        )
        Spacer(modifier = Modifier.height(20.dp))


        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confpassword by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        // Function to determine visual transformation based on visibility
        val visualTransformation: VisualTransformation =
            if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation()
        // Function to switch the password visibility
        fun togglePasswordVisibility() {
            passwordVisible = !passwordVisible
        }


        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            shape = RoundedCornerShape(10.dp),
            onValueChange = {name = it},
            label = {Text(text = "Full Name")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person", tint = Color.Magenta)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

        )
        Spacer(modifier = Modifier.height(20.dp))
        
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text(text = "Email Address")},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "", tint = Color.Magenta)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            shape = RoundedCornerShape(10.dp),
            onValueChange = {password = it},
            label = {Text(text = "Password")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "", tint = Color.Magenta)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = visualTransformation,
            trailingIcon = {
                val icon = if (passwordVisible) {

                    painterResource(id = R.drawable.passwordshow)
                } else {

                    painterResource(id = R.drawable.passwordhide)
                }
                IconButton(onClick = { togglePasswordVisibility() }) {
                    Icon(painter = icon, contentDescription = null)
                }
            }



        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = confpassword,
            shape = RoundedCornerShape(10.dp),
            onValueChange = {confpassword = it},
            label = {Text(text = "Confirm Password")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "", tint = Color.Magenta)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = visualTransformation,
            trailingIcon = {
                val icon = if (passwordVisible) {

                    painterResource(id = R.drawable.passwordshow)
                } else {

                    painterResource(id = R.drawable.passwordhide)
                }
                IconButton(onClick = { togglePasswordVisibility() }) {
                    Icon(painter = icon, contentDescription = null)
                }
            }

        )

        Spacer(modifier = Modifier.height(20.dp))

        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context)

        Button(
            onClick = {authViewModel.signup(name, email, password,confpassword)},
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(50.dp)
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(Color.Magenta),
            shape = RoundedCornerShape(10.dp)
        ) {

            Text(text = ("Create An Account"))

        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate(ROUT_LOGIN)
            },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(50.dp)
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(Color.Magenta),
            shape = RoundedCornerShape(10.dp)
        ) {

            Text(text = ("Login"))

        }




    }

}




@Composable
@Preview(showBackground = true)
fun SignupScreenPreview(){
    SignupScreen(rememberNavController())


}