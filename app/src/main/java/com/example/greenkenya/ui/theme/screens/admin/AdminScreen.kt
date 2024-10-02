package com.example.greenkenya.ui.theme.screens.admin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.greenkenya.R
import com.example.greenkenya.navigation.ROUT_CONTACT
import com.example.greenkenya.navigation.ROUT_HOME
import com.example.greenkenya.navigation.ROUT_LOGIN
import com.example.greenkenya.navigation.ROUT_REGISTER
import com.example.greenkenya.navigation.ROUT_REPORT
import com.example.greenkenya.navigation.ROUT_VIEW
import com.example.greenkenya.navigation.ROUT_VIEWREPORT
import com.example.greenkenya.navigation.VIEW_ACCOUNT
import com.example.greenkenya.ui.theme.Green


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun AdminScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopAppBar(
            title = { Text(text = "Admin Page", color = Color.Black) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Green),
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = Color.Black
                    )

                }
            }

        )

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//Start of Main Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(900.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {

                //Row 1
                Row(modifier = Modifier.padding(20.dp)) {
                    //Card 1
                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(180.dp)
                            .clickable { navController.navigate(ROUT_VIEWREPORT) },
                        colors = CardDefaults.cardColors(Green),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {

                        Column {
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.viewreport),
                                    contentDescription = "report",
                                    modifier = Modifier.size(100.dp)
                                )

                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = "View Reports and Complaints",
                                fontSize = 18.sp,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    //Card 2
                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(180.dp)
                            .clickable { navController.navigate(VIEW_ACCOUNT) },
                        colors = CardDefaults.cardColors(Green),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {

                        Column {
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.account),
                                    contentDescription = "accounts",
                                    modifier = Modifier.size(100.dp)
                                )

                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "View Registered Accounts",
                                fontSize = 18.sp,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }

                //End of Row 1

                //Row 2

                }
            }

//End of Main Card




    }
}
@Composable
@Preview(showBackground = true)
fun AdminScreenPreview() {
    AdminScreen(rememberNavController())


}
