package com.example.greenkenya.ui.theme.screens.report

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.greenkenya.R
import com.example.greenkenya.data.TaskViewModel
import com.example.greenkenya.navigation.ROUT_ABOUT
import com.example.greenkenya.navigation.ROUT_CONTACT
import com.example.greenkenya.navigation.ROUT_HOME
import com.example.greenkenya.navigation.ROUT_VIEWREPORT
import com.example.greenkenya.ui.theme.Green


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        Column (
            modifier = Modifier.padding(horizontal = 12.dp)
        ){

            var taskName by remember { mutableStateOf("") }
            var taskDescription by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.height(30.dp))

            // home icon
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    modifier = Modifier.clickable { navController.navigate(ROUT_HOME) },
                    tint = Color.Magenta

                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Home",
                    modifier = Modifier.clickable { navController.navigate(ROUT_HOME) },
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            navController.navigate(ROUT_CONTACT)
                        },
                        tint = Color.Magenta
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Help",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable {
                            navController.navigate(ROUT_ABOUT)
                        }
                    )
                }
            }
            //end of home icon
            //intro row
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                //card holding icon
                Card (
                    modifier = Modifier
                        .size(70.dp),
                    shape = RoundedCornerShape(50),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ){
                    Image(
                        painter = painterResource(id = R.drawable.redflag),
                        contentDescription = "top icon",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp)
                    )
                }
                //end of card holding icon

            }
            Text(
                text = "Input Complaint",
                fontSize = 26.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black
            )
            // intro row end
            Spacer(modifier = Modifier.height(5.dp))

            Row (
                modifier= Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){


            }

            Spacer(modifier = Modifier.height(28.dp))


            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                placeholder = { Text(text="eg. John Doe") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "icon"
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "Enter Your Name") }
            )

            Spacer(modifier = Modifier.height(13.dp))

            OutlinedTextField(
                value = taskDescription,
                onValueChange = { taskDescription = it },
                placeholder = { Text(text="eg. This is a complaint about...") },
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(
                    text = "Complaints",
                    fontSize = 17.sp,
                    textDecoration = TextDecoration.Underline
                )
                },
            )

            Spacer(modifier = Modifier.height(15.dp))

            val modifier = Modifier
            val context = LocalContext.current
            TaskUploadButton(modifier,context, navController , taskName.trim() , taskDescription.trim() )

        }
    }
}

@Composable
fun TaskUploadButton(modifier: Modifier = Modifier, context: Context, navController: NavController, name:String, description:String){
    Button(onClick = {
        val taskRepository = TaskViewModel(navController,context)
        taskRepository.uploadTask(name, description)
    },
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(Color.Magenta),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 20.dp)
    ) {
        Text(
            text = "Report",
            fontSize = 17.sp
        )
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ReportScreenPreview(){
    ReportScreen(navController = rememberNavController())
}