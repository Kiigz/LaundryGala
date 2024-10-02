package com.example.greenkenya.ui.theme.screens.contacts

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.greenkenya.R
import com.example.greenkenya.ui.theme.Green


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun ContactScreen(navController: NavController){
    var selected by remember { mutableIntStateOf(0) }



        Column (modifier= Modifier.fillMaxSize()){



            Scaffold(
                bottomBar = {

                    BottomNavBar(
                        navController = navController,
                        selected = selected,
                        onItemSelected = { index -> selected = index }
                    )
                },



                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "LaundryGala", color = Color.Magenta,fontFamily = FontFamily.Cursive ) },
                        colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Black),
                        navigationIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.White
                                )

                            }
                        }

                    )
                },
//          Floation Action Button

                floatingActionButton = {
                    FloatingActionButton(onClick = { /*TODO*/ }) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Add,
                                contentDescription = "menu")
                        }
                    }
                },
                //Content Section
                content = @Composable{
                    Column(
                        modifier = Modifier
                            .background(color = Color.Black)

                            .fillMaxSize()
                            .padding(top = 60.dp)

                    ){

                        //ROW 1

                        Row (modifier = Modifier.padding(top = 40.dp, start = 20.dp)){

                            //CARD 1

                            Card(
                                modifier = Modifier
                                    .height(180.dp)
                                    .width(200.dp)
                            ) {

                                Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                                    Image(
                                        painter = painterResource(id = R.drawable.customercare),
                                        contentDescription ="home",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop //TO FILL THE WHOLE CARD
                                    )



                                    Spacer(modifier = Modifier.height(20.dp))
                                }

                            }  //END OF CARD 1

                            Column(modifier = Modifier.padding(start = 20.dp)) {
                                Text(text = "Customer Care", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                Text(text = "Our Customer Care Service is at your beck and call and is ready to attend to your trash pickup needs.")
                                Row {

                                }

                                val mContext= LocalContext.current

                                Button(
                                    onClick = {
                                        val callIntent= Intent(Intent.ACTION_DIAL)
                                        callIntent.data="tel:0700048750".toUri()
                                        mContext.startActivity(callIntent)

                                    },
                                    colors = ButtonDefaults.buttonColors(Green),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(text = "Call")

                                }
                            }

                        }
                        //END OF ROW 1
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "You can reach us through the various methods below:", fontSize = 20.sp, textAlign = TextAlign.Center, fontFamily = FontFamily.Serif)
                        Spacer(modifier = Modifier.height(30.dp))


                        val mContext= LocalContext.current




                        Button(
                            onClick = {

                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(start = 20.dp, end = 20.dp),
                            colors = ButtonDefaults.buttonColors(Green),
                            shape = RoundedCornerShape(10.dp)
                        ) {

                            Text(text = "Mpesa")

                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        Button(
                            onClick = {

                                val smsIntent= Intent(Intent.ACTION_SENDTO)
                                smsIntent.data="smsto:0700048750".toUri()
                                smsIntent.putExtra("sms_body","Hello, Clarence here, how can we help?")
                                mContext.startActivity(smsIntent)

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(start = 20.dp, end = 20.dp),
                            colors = ButtonDefaults.buttonColors(Green),
                            shape = RoundedCornerShape(10.dp)
                        ) {

                            Text(text = "Send a Text")

                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        Button(
                            onClick = {

                                val shareIntent = Intent(Intent.ACTION_SEND)
                                shareIntent.type = "text/plain"
                                shareIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("thisisclarence1@gmail.com"))
                                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "subject")
                                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello, this is the email body")
                                mContext.startActivity(shareIntent)

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(start = 20.dp, end = 20.dp),
                            colors = ButtonDefaults.buttonColors(Green),
                            shape = RoundedCornerShape(10.dp)
                        ) {

                            Text(text = "Send us an email")

                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        Button(
                            onClick = {

                                val shareIntent= Intent(Intent.ACTION_SEND)
                                shareIntent.type="text/plain"
                                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out my new app")
                                mContext.startActivity(Intent.createChooser(shareIntent, "Share"))

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(start = 20.dp, end = 20.dp),
                            colors = ButtonDefaults.buttonColors(Green),
                            shape = RoundedCornerShape(10.dp)
                        ) {

                            Text(text = "Share this app")

                        }

                        Spacer(modifier = Modifier.height(25.dp))




                        Button(
                            onClick = {

                                val cameraIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                if (cameraIntent.resolveActivity(mContext.packageManager)!=null){
                                    mContext.startActivity(cameraIntent)
                                }else{
                                    println("Camera app is not available")
                                }

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(start = 20.dp, end = 20.dp),
                            colors = ButtonDefaults.buttonColors(Green),
                            shape = RoundedCornerShape(10.dp)
                        ) {

                            Text(text = "Camera")

                        }


                    }

                }



                //END OF CONTENT SECTION

            )


        }

    }

@Composable
fun BottomNavBar(navController: NavController, selected: Int, onItemSelected: (Int) -> Unit) {
    NavigationBar(containerColor = Color.Black) {
        bottomNavItems.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(
                selected = index == selected,
                onClick = {
                    onItemSelected(index)
                    navController.navigate(bottomNavItem.route)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (bottomNavItem.badges != 0) {
                                Badge {
                                    Text(text = bottomNavItem.badges.toString())
                                }
                            } else if (bottomNavItem.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector =
                            if (index == selected) bottomNavItem.selectedIcon
                            else bottomNavItem.unselectedIcon,
                            contentDescription = bottomNavItem.title
                        )
                    }
                },
                label = {
                    Text(text = bottomNavItem.title)
                }
            )
        }
    }
}


    val bottomNavItems = listOf(
        BottomNavItem(
            title = "Home",
            route="home",
            selectedIcon= Icons.Filled.Home,
            unselectedIcon= Icons.Outlined.Home,
            hasNews = true,
            badges=0
        ),

        BottomNavItem(
            title = "Pickups",
            route="view_products",
            selectedIcon=Icons.Filled.Star,
            unselectedIcon=Icons.Outlined.Star,
            hasNews = false,
            badges=0
        ),



        BottomNavItem(
            title = "Login",
            route="login",
            selectedIcon= Icons.Filled.Person,
            unselectedIcon= Icons.Outlined.Person,
            hasNews = true,
            badges=0
        ),

        BottomNavItem(
            title = "Signup",
            route="signup",
            selectedIcon= Icons.Filled.Face,
            unselectedIcon= Icons.Outlined.Face,
            hasNews = true,
            badges=0
        ),
        )



    data class BottomNavItem(
        val title :String,
        val route :String,
        val selectedIcon: ImageVector,
        val unselectedIcon : ImageVector,
        val hasNews :Boolean,
        val badges :Int
    )







@Composable
@Preview(showBackground = true)
fun ContactScreenPreview(){
    ContactScreen(rememberNavController())


}