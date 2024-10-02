package com.example.greenkenya.ui.theme.screens.about

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.greenkenya.R
import com.example.greenkenya.ui.theme.DarkGreen
import java.util.Calendar


@Composable
fun AboutScreen(navController: NavController){

    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Image(
            painter = painterResource(id = R.drawable.recycleicon),
            contentDescription ="home",
            modifier = Modifier
                .size(100.dp),
            contentScale = ContentScale.Crop

        )

        Text(
            text = "GreenKenya",
            fontSize = 40.sp,
            fontFamily = FontFamily.Serif,
            color = DarkGreen

        )


        Spacer(modifier = Modifier.height(20.dp))



        //DateField
        var selectedDate by remember { mutableStateOf<String?>(null) }
        var showDatePicker by remember { mutableStateOf(false) }



        Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)){

            Button(onClick = {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                DatePickerDialog(
                    //Don't forget to create the context variable located just below
                    //the aboutscreen function
                    context,
                    { _, selectedYear, selectedMonth, selectedDay ->
                        selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                    },
                    year,
                    month,
                    day
                ).show()
            },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color.Gray),
                modifier = Modifier
                    .height(65.dp)
                    .padding(top = 10.dp)) {
                Text(text = "Start Date")
            }
            Spacer(modifier = Modifier.width(20.dp))

            OutlinedTextField(
                value = selectedDate ?: "",
                onValueChange = { /* No-op, as we handle value through date picker */ },
                label = { Text("Choose Collection Date") },
                readOnly = true,  // Makes the text field non-editable
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .width(250.dp),
                trailingIcon = {
                    Text(text = "📅")  // Icon to indicate date picker
                },
                singleLine = true
            )


        }

        //End of a datefield





        //Start of radiobutton
        val selectedOption = remember { mutableStateOf("1. Plastic Waste (Ksh. 1200 p.m)") }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Choose Collection Package",
                fontSize = 20.sp
            )
            listOf("1. Plastic Waste (Ksh. 1200 p.m)", "2. Organic Waste (Ksh. 950 p.m)", "3. General Waste(1+2) (Ksh. 1800 p.m)").forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 1.dp)
                ) {
                    RadioButton(
                        selected = selectedOption.value == option,
                        onClick = { selectedOption.value = option },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Green,
                            unselectedColor = Color.Red
                        )
                    )
                    Text(
                        text = option,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        //End of radiobutton









    }


}


@Composable
@Preview(showBackground = true)
fun AboutScreenPreview(){
    AboutScreen(rememberNavController())
}