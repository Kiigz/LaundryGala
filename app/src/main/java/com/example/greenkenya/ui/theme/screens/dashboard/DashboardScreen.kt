package com.example.greenkenya.ui.theme.screens.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.greenkenya.NavDrawer.DrawerBody

import com.example.greenkenya.NavDrawer.MenuItem
import com.example.greenkenya.R
import com.example.greenkenya.navigation.ADD_ACCOUNT
import com.example.greenkenya.navigation.ADD_PRODUCTS_URL
import com.example.greenkenya.navigation.ROUT_ABOUT
import com.example.greenkenya.navigation.ROUT_CONTACT
import com.example.greenkenya.navigation.ROUT_HOME
import com.example.greenkenya.navigation.ROUT_LOGIN
import com.example.greenkenya.navigation.ROUT_MAP
import com.example.greenkenya.navigation.ROUT_REGISTER
import com.example.greenkenya.navigation.ROUT_REPORT
import com.example.greenkenya.navigation.ROUT_VIEW
import com.example.greenkenya.navigation.VIEW_ACCOUNT
import com.example.greenkenya.ui.theme.Green
import com.example.greenkenya.ui.theme.screens.contacts.BottomNavBar
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable



fun DashboardScreen(navController: NavController) {
    var selected by remember { mutableIntStateOf(0) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()





            ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
                ModalDrawerSheet {

                    DrawerBody(

                        items = listOf(
                            MenuItem(
                                id = "home",
                                title = "Home",
                                contentDescription = "Go to home screen",
                                icon = Icons.Default.Home

                            ),
                            MenuItem(
                                id = "settings",
                                title = "Settings",
                                contentDescription = "Go to settings screen",
                                icon = Icons.Default.Settings
                            ),
                            MenuItem(
                                id = "help",
                                title = "Help",
                                contentDescription = "Get help",
                                icon = Icons.Default.Info
                            ),
                            MenuItem(
                                id = "report",
                                title = "Report",
                                contentDescription = "Get help",
                                icon = Icons.Default.Info
                            ),
                        ),
                        onItemClick = { menuItem ->
                            // Handle navigation based on the clicked item
                            when (menuItem.id) {
                                "Home" -> navController.navigate("home")
                                "Contact Us" -> navController.navigate("contact")
                                "report" -> navController.navigate("report")
                            }

                            // Close the drawer after navigation
                            scope.launch {
                                drawerState.close()
                            }
                        },
                    )

                }

            }
                )
            {

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
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()  // Open drawer when navigation icon is clicked
                                }
                            }) {
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
                        IconButton(onClick = { navController.navigate(ADD_PRODUCTS_URL) }) {
                            Icon(imageVector = Icons.Default.Create,
                                contentDescription = "menu")
                        }
                    }
                },
                //Content Section
                content = @Composable
                {paddingValues ->
                    Column(modifier = Modifier
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())) {
//Start of Main Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(900.dp),
                        colors = CardDefaults.cardColors(Color.Black)
                    ) {
                        Row(modifier = Modifier.padding(top = 10.dp, start = 10.dp, bottom = 0.5.dp)) {
                            Text(text = "Recent..", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }

                        //Row 1
                        Row(modifier = Modifier.padding(20.dp)) {
                            //Card 1
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .clickable { navController.navigate(ROUT_HOME) },
                                colors = CardDefaults.cardColors(Color.DarkGray),
                                elevation = CardDefaults.cardElevation(10.dp)
                            ) {

                                Column {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {


                                    }
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Text(

                                        color = Color.White,
                                        text = "Orders",
                                        fontSize = 40.sp,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Left,

                                    )
                                }

                            }
                            Spacer(modifier = Modifier.width(30.dp))
                            //Card 2

                        }

                        //End of Row 1

                        //Row 2
//                        Services
                        Row(modifier = Modifier.padding(top = 10.dp, start = 10.dp, bottom = 0.5.dp)) {
                            Text(text = "Services..", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(modifier = Modifier
                            .padding(start = 20.dp)
                            .horizontalScroll(rememberScrollState())) {
                            //Card 1
                            Card(
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(350.dp)
                            ) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                                    Image(
                                        painter = painterResource(id = R.drawable.ironing),
                                        contentDescription = "home",
                                        modifier = Modifier.fillMaxSize().clickable { navController.navigate(ROUT_REGISTER)},
                                        contentScale = ContentScale.Crop



                                    )



                                }

                            }

                            //End of card
                            Spacer(modifier = Modifier.width(20.dp))


                            //Card 2
                            Card(
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(350.dp)
                            ) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                                    Image(
                                        painter = painterResource(id = R.drawable.laundry),
                                        contentDescription = "home",
                                        modifier = Modifier.fillMaxSize().clickable { navController.navigate(ROUT_REGISTER)},
                                        contentScale = ContentScale.Crop
                                    )

                                }

                            }

                            //End of card
                            Spacer(modifier = Modifier.width(20.dp))


                            //Card 3
                            Card(
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(350.dp)
                            ) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                                    Image(
                                        painter = painterResource(id = R.drawable.stainremoval),
                                        contentDescription = "home",
                                        modifier = Modifier.fillMaxSize().clickable { navController.navigate(ROUT_REGISTER)},
                                        contentScale = ContentScale.Crop
                                    )

                                }

                            }

                            //End of card
                        }

                        //End of Row 2

                        //Row 3
                        Row(modifier = Modifier.padding(20.dp))
                        {
                            val mContext = LocalContext.current
                            //Card 5
                            Card(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(180.dp)
                                    .clickable {
                                        val simToolKitLaunchIntent =
                                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                        simToolKitLaunchIntent?.let { mContext.startActivity(it) }
                                    },
                                colors = CardDefaults.cardColors(Color.Black),
                                elevation = CardDefaults.cardElevation(10.dp)
                            )
                            {

                                Column {
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.pay2),
                                            contentDescription = "pay",
                                            modifier = Modifier.size(100.dp)
                                        )

                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        color = Color.White,
                                        text = "Make Payments",
                                        fontSize = 18.sp,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center

                                    )
                                }

                            }
                            Spacer(modifier = Modifier.width(30.dp))
                            //Card 6
                            Card(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(180.dp)
                                    .clickable { navController.navigate(ROUT_REGISTER) },
                                colors = CardDefaults.cardColors(Color.Black),
                                elevation = CardDefaults.cardElevation(10.dp)
                            ) {

                                Column {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.map),
                                            contentDescription = "invoice",
                                            modifier = Modifier.size(100.dp)
                                        )

                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        color = Color.White,
                                        text = "Our Location",
                                        fontSize = 18.sp,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }

                            }
                        }

                        //End of Row 3
                        //Row 4
                        Row(modifier = Modifier.padding(20.dp)) {
                            //Card 7
                            val mContext = LocalContext.current

                            Card(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(180.dp)
                                    .clickable {
                                        val shareIntent = Intent(Intent.ACTION_SEND)
                                        shareIntent.type = "text/plain"
                                        shareIntent.putExtra(
                                            Intent.EXTRA_TEXT,
                                            "Check out this garbage pickup app."
                                        )
                                        mContext.startActivity(
                                            Intent.createChooser(
                                                shareIntent,
                                                "Share"
                                            )
                                        )

                                    },
                                colors = CardDefaults.cardColors(Color.Black),
                                elevation = CardDefaults.cardElevation(10.dp)
                            ) {

                                Column {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.share),
                                            contentDescription = "report",
                                            modifier = Modifier.size(100.dp)
                                        )

                                    }
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Text(
                                        text = "Share This App",
                                        fontSize = 18.sp,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }

                            }
                            Spacer(modifier = Modifier.width(30.dp))
                            //Card 8
                            Card(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(180.dp)
                                    .clickable { navController.navigate(ROUT_LOGIN) },
                                colors = CardDefaults.cardColors(Color.Black),
                                elevation = CardDefaults.cardElevation(10.dp)
                            ) {

                                Column {
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.administrator),
                                            contentDescription = "admin",
                                            modifier = Modifier.size(100.dp)
                                        )

                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "Admin",
                                        fontSize = 18.sp,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }

                            }


//End of Row 4

                        }
                    }

//End of Main Card
                }}



                //END OF CONTENT SECTION

            )}


//            Scaffold(
//
//                bottomBar = {
//
//                    BottomNavBar(
//                        navController = navController,
//                        selected = selected,
//                        onItemSelected = { index -> selected = index }
//                    )
//                },
//
//
//
//                topBar = {
//                    CenterAlignedTopAppBar(
//                        title = { Text(text = "LaundryGala", color = Color.Magenta,fontFamily = FontFamily.Cursive ) },
//                        colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Black),
//                        navigationIcon = {
//                            IconButton(onClick =  onNavigationIconClick) {
//                                Icon(
//                                    imageVector = Icons.Default.Menu,
//                                    contentDescription = "Menu",
//                                    tint = Color.White
//                                )
//
//                            }
//                        }
//
//                    )
//                },
//
//
//
////          Floation Action Button
//
//                floatingActionButton = {
//                    FloatingActionButton(onClick = { /*TODO*/ }) {
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(imageVector = Icons.Default.Create,
//                                contentDescription = "menu")
//                        }
//                    }
//                },
//                //Content Section
//                content = @Composable{Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
////Start of Main Card
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(900.dp),
//                        colors = CardDefaults.cardColors(Color.Black)
//                    ) {
//
//                        //Row 1
//                        Row(modifier = Modifier.padding(20.dp)) {
//                            //Card 1
//                            Card(
//                                modifier = Modifier
//                                    .width(150.dp)
//                                    .height(180.dp)
//                                    .clickable { navController.navigate(ROUT_HOME) },
//                                colors = CardDefaults.cardColors(Color.Black),
//                                elevation = CardDefaults.cardElevation(10.dp)
//                            ) {
//
//                                Column {
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                    Box(
//                                        modifier = Modifier.fillMaxWidth(),
//                                        contentAlignment = Alignment.Center
//                                    ) {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.home),
//                                            contentDescription = "home",
//                                            modifier = Modifier.size(100.dp)
//                                        )
//
//                                    }
//                                    Spacer(modifier = Modifier.height(15.dp))
//                                    Text(
//                                        text = "Home",
//                                        fontSize = 18.sp,
//                                        modifier = Modifier.fillMaxWidth(),
//                                        textAlign = TextAlign.Center
//                                    )
//                                }
//
//                            }
//                            Spacer(modifier = Modifier.width(30.dp))
//                            //Card 2
//                            Card(
//                                modifier = Modifier
//                                    .width(150.dp)
//                                    .height(180.dp)
//                                    .clickable { navController.navigate(ROUT_CONTACT) },
//                                colors = CardDefaults.cardColors(Green),
//                                elevation = CardDefaults.cardElevation(10.dp)
//                            ) {
//
//                                Column {
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                    Box(
//                                        modifier = Modifier.fillMaxWidth(),
//                                        contentAlignment = Alignment.Center
//                                    ) {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.contact),
//                                            contentDescription = "contacts",
//                                            modifier = Modifier.size(100.dp)
//                                        )
//
//                                    }
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                    Text(
//                                        text = "Contacts",
//                                        fontSize = 18.sp,
//                                        modifier = Modifier.fillMaxWidth(),
//                                        textAlign = TextAlign.Center
//                                    )
//                                }
//
//                            }
//                        }
//
//                        //End of Row 1
//
//                        //Row 2
//                        Row(modifier = Modifier.padding(20.dp)) {
//                            //Card 3
//
//                            Card(
//                                modifier = Modifier
//                                    .width(150.dp)
//                                    .height(180.dp)
//                                    .clickable { navController.navigate(ROUT_REPORT) },
//                                colors = CardDefaults.cardColors(Green),
//                                elevation = CardDefaults.cardElevation(10.dp)
//                            ) {
//
//                                Column {
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                    Box(
//                                        modifier = Modifier.fillMaxWidth(),
//                                        contentAlignment = Alignment.Center
//                                    ) {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.redflag),
//                                            contentDescription = "report",
//                                            modifier = Modifier.size(100.dp)
//                                        )
//
//                                    }
//                                    Spacer(modifier = Modifier.height(20.dp))
//                                    Text(
//                                        text = "Report Missed Pickup",
//                                        fontSize = 18.sp,
//                                        modifier = Modifier.fillMaxWidth(),
//                                        textAlign = TextAlign.Center
//                                    )
//                                }
//
//                            }
//                            Spacer(modifier = Modifier.width(30.dp))
//                            //Card 4
//                            Card(
//                                modifier = Modifier
//                                    .width(150.dp)
//                                    .height(180.dp)
//                                    .clickable { navController.navigate(ADD_ACCOUNT) },
//                                colors = CardDefaults.cardColors(Green),
//                                elevation = CardDefaults.cardElevation(10.dp)
//                            ) {
//
//                                Column {
//                                    Spacer(modifier = Modifier.height(20.dp))
//                                    Box(
//                                        modifier = Modifier.fillMaxWidth(),
//                                        contentAlignment = Alignment.Center
//                                    ) {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.register),
//                                            contentDescription = "register",
//                                            modifier = Modifier.size(100.dp)
//                                        )
//
//                                    }
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                    Text(
//                                        text = "Register",
//                                        fontSize = 18.sp,
//                                        modifier = Modifier.fillMaxWidth(),
//                                        textAlign = TextAlign.Center
//                                    )
//                                }
//
//                            }
//                        }
//
//                        //End of Row 2
//
//                        //Row 3
//                        Row(modifier = Modifier.padding(20.dp))
//                        {
//                            val mContext = LocalContext.current
//                            //Card 5
//                            Card(
//                                modifier = Modifier
//                                    .width(150.dp)
//                                    .height(180.dp)
//                                    .clickable {
//                                        val simToolKitLaunchIntent =
//                                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                                        simToolKitLaunchIntent?.let { mContext.startActivity(it) }
//                                    },
//                                colors = CardDefaults.cardColors(Green),
//                                elevation = CardDefaults.cardElevation(10.dp)
//                            ) {
//
//                                Column {
//                                    Spacer(modifier = Modifier.height(15.dp))
//                                    Box(
//                                        modifier = Modifier.fillMaxWidth(),
//                                        contentAlignment = Alignment.Center
//                                    ) {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.pay2),
//                                            contentDescription = "pay",
//                                            modifier = Modifier.size(100.dp)
//                                        )
//
//                                    }
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                    Text(
//                                        text = "Make Payments",
//                                        fontSize = 18.sp,
//                                        modifier = Modifier.fillMaxWidth(),
//                                        textAlign = TextAlign.Center
//                                    )
//                                }
//
//                            }
//                            Spacer(modifier = Modifier.width(30.dp))
//                            //Card 6
//                            Card(
//                                modifier = Modifier
//                                    .width(150.dp)
//                                    .height(180.dp)
//                                    .clickable { navController.navigate(ROUT_MAP) },
//                                colors = CardDefaults.cardColors(Green),
//                                elevation = CardDefaults.cardElevation(10.dp)
//                            ) {
//
//                                Column {
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                    Box(
//                                        modifier = Modifier.fillMaxWidth(),
//                                        contentAlignment = Alignment.Center
//                                    ) {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.map),
//                                            contentDescription = "invoice",
//                                            modifier = Modifier.size(100.dp)
//                                        )
//
//                                    }
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                    Text(
//                                        text = "Our Location",
//                                        fontSize = 18.sp,
//                                        modifier = Modifier.fillMaxWidth(),
//                                        textAlign = TextAlign.Center
//                                    )
//                                }
//
//                            }
//                        }
//
//                        //End of Row 3
//                        //Row 4
//                        Row(modifier = Modifier.padding(20.dp)) {
//                            //Card 7
//                            val mContext = LocalContext.current
//
//                            Card(
//                                modifier = Modifier
//                                    .width(150.dp)
//                                    .height(180.dp)
//                                    .clickable {
//                                        val shareIntent = Intent(Intent.ACTION_SEND)
//                                        shareIntent.type = "text/plain"
//                                        shareIntent.putExtra(
//                                            Intent.EXTRA_TEXT,
//                                            "Check out this garbage pickup app."
//                                        )
//                                        mContext.startActivity(
//                                            Intent.createChooser(
//                                                shareIntent,
//                                                "Share"
//                                            )
//                                        )
//
//                                    },
//                                colors = CardDefaults.cardColors(Green),
//                                elevation = CardDefaults.cardElevation(10.dp)
//                            ) {
//
//                                Column {
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                    Box(
//                                        modifier = Modifier.fillMaxWidth(),
//                                        contentAlignment = Alignment.Center
//                                    ) {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.share),
//                                            contentDescription = "report",
//                                            modifier = Modifier.size(100.dp)
//                                        )
//
//                                    }
//                                    Spacer(modifier = Modifier.height(20.dp))
//                                    Text(
//                                        text = "Share This App",
//                                        fontSize = 18.sp,
//                                        modifier = Modifier.fillMaxWidth(),
//                                        textAlign = TextAlign.Center
//                                    )
//                                }
//
//                            }
//                            Spacer(modifier = Modifier.width(30.dp))
//                            //Card 8
//                            Card(
//                                modifier = Modifier
//                                    .width(150.dp)
//                                    .height(180.dp)
//                                    .clickable { navController.navigate(ROUT_LOGIN) },
//                                colors = CardDefaults.cardColors(Green),
//                                elevation = CardDefaults.cardElevation(10.dp)
//                            ) {
//
//                                Column {
//                                    Spacer(modifier = Modifier.height(20.dp))
//                                    Box(
//                                        modifier = Modifier.fillMaxWidth(),
//                                        contentAlignment = Alignment.Center
//                                    ) {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.administrator),
//                                            contentDescription = "admin",
//                                            modifier = Modifier.size(100.dp)
//                                        )
//
//                                    }
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                    Text(
//                                        text = "Admin",
//                                        fontSize = 18.sp,
//                                        modifier = Modifier.fillMaxWidth(),
//                                        textAlign = TextAlign.Center
//                                    )
//                                }
//
//                            }
//
//
////End of Row 4
//
//                        }
//                    }
//
////End of Main Card
//                }}
//
//
//
//                //END OF CONTENT SECTION
//
//            )










            }







    @Composable
    @Preview(showBackground = true)
    fun DashboardScreenPreview() {
        DashboardScreen(rememberNavController() )


    }