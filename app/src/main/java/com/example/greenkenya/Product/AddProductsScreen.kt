package com.example.greenkenya.Product

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.greenkenya.R
import com.example.greenkenya.data.ProductViewModel
import com.example.greenkenya.navigation.ADD_PRODUCTS_URL
import com.example.greenkenya.ui.theme.Green


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductsScreen(navController:NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var selected by remember { mutableIntStateOf(0) }
        Scaffold(
            bottomBar = {
                NavigationBar (
                    containerColor = Color.Black,
                    contentColor = Color.Black){
                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = index == selected,
                            onClick = {
                                selected = index
                                navController.navigate(bottomNavItem.route)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (bottomNavItem.badges != 0) {
                                            Badge (containerColor = Color.White){
                                                Text(text = bottomNavItem.badges.toString())
                                            }
                                        } else if (bottomNavItem.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(imageVector =
                                    if (index == selected)
                                        bottomNavItem.selectedIcon
                                    else
                                        bottomNavItem.unselectedIcon,
                                        contentDescription = bottomNavItem.title)
                                }

                            },
                            label = {
                                Text(text = bottomNavItem.title)
                            }
                        )
                    }
                }
            },

            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    containerColor = Color.LightGray
                ) {
                    IconButton(onClick = {
                        navController.navigate(ADD_PRODUCTS_URL)
                    }) {
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "menu")
                    }
                }
            },
            //Content Section
            content = @Composable{
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(Color.Black)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){


                    Spacer(modifier = Modifier.height(50.dp))

                    Text(
                        text = "Order  Here!",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.White

                             )

                    var productName by remember { mutableStateOf("") }
                    var productPrice by remember { mutableStateOf("") }
                    var phone by remember { mutableStateOf("") }
                    val context = LocalContext.current
                    var mExpanded by remember { mutableStateOf(false) }
                    val options2 = listOf("laundry", "Staining", "")

                    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
                    val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown




                    Spacer(modifier = Modifier.height(10.dp))

                    Column(Modifier.padding(20.dp)) {
                        OutlinedTextField(
                            value = productName,
                            onValueChange = { productName = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            label = {Text("Choose clothing weight")},
                            trailingIcon = {
                                Icon(icon,"contentDescription",
                                    Modifier.clickable { mExpanded = !mExpanded })
                            }
                        )
                        DropdownMenu(
                            expanded = mExpanded,
                            onDismissRequest = { mExpanded = false },
                            modifier = Modifier.width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
                        ) {
                            options2.forEach {

                                    label -> DropdownMenuItem(
                                text = { Text(text = label)},
                                onClick = {
                                    productName = label
                                    mExpanded = false
                                })


                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))


                    // Start of Text Field with a dropdown
                    var mExpanded1 by remember { mutableStateOf(false) }
                    val options = listOf("250g", "500g", "1 kg")
                    var productQuantity by remember { mutableStateOf("") }
                    var mTextFieldSize1 by remember { mutableStateOf(Size.Zero)}
                    val icon1 = if (mExpanded1) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

                    Column(Modifier.padding(20.dp)) {
                        OutlinedTextField(
                            value = productQuantity,
                            onValueChange = { productQuantity = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    mTextFieldSize1 = coordinates.size.toSize()
                                },
                            label = {Text("Choose clothing weight")},
                            trailingIcon = {
                                Icon(icon1,"contentDescription",
                                    Modifier.clickable { mExpanded1 = !mExpanded1 })
                            }
                        )
                        DropdownMenu(
                            expanded = mExpanded1,
                            onDismissRequest = { mExpanded1 = false },
                            modifier = Modifier.width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
                        ) {
                            options.forEach {

                                    label -> DropdownMenuItem(
                                text = { Text(text = label)},
                                onClick = {
                                    productQuantity = label
                                    mExpanded1 = false
                                })


                            }
                        }
                    }
                    //End of TextField with dropdown

                    //End of Textfield with dropdown

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = productPrice,
                        onValueChange = { productPrice = it },
                        label = { Text(text = "Product price e.g Ksh.500") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text(text = "Phone") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    Spacer(modifier = Modifier.height(20.dp))


//                    Make Payments Start

//                    val mContext = LocalContext.current
//                    Column {
//                        Card(
//                            modifier = Modifier
//                                .width(150.dp)
//                                .height(150.dp)
//                                .clickable {
//                                    val simToolKitLaunchIntent =
//                                        mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                                    simToolKitLaunchIntent?.let { mContext.startActivity(it) }
//                                },
//                            colors = CardDefaults.cardColors(Green),
//                            elevation = CardDefaults.cardElevation(10.dp)
//                        )
//                        {
//
//                            Column {
//                                Spacer(modifier = Modifier.height(15.dp))
//                                Box(
//                                    modifier = Modifier.fillMaxWidth(),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    Image(
//                                        painter = painterResource(id = R.drawable.pay2),
//                                        contentDescription = "pay",
//                                        modifier = Modifier.size(100.dp)
//                                    )
//
//                                }
//                                Spacer(modifier = Modifier.height(10.dp))
//                                Text(
//                                    text = "Make Payments",
//                                    fontSize = 18.sp,
//                                    modifier = Modifier.fillMaxWidth(),
//                                    textAlign = TextAlign.Center
//                                )
//                            }
//
//                        }
//                    }
//                    Payment end



                    //---------------------IMAGE PICKER START-----------------------------------//

                    var modifier = Modifier
                    ImagePicker(modifier,context, navController, productName.trim(), productQuantity.trim(), productPrice.trim(),phone.trim())

                    //---------------------IMAGE PICKER END-----------------------------------//



                }




            }

        )

    }
}



val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route="home",
        selectedIcon= Icons.Filled.Home,
        unselectedIcon= Icons.Outlined.Home,
        hasNews = false,
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

//Image Select Start
@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context,navController: NavHostController, name:String, quantity:String, price:String,phone:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceEvenly // Distribute items evenly across the row
    ) {
        // First Card: Image Selection
        Column {
            if (hasImage && imageUri != null) {
                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Selected image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Card(

                modifier = Modifier
                    .width(150.dp)
                    .height(180.dp)
                    .clickable {
                        imagePicker.launch("image/*")
                    },
                colors = CardDefaults.cardColors(Color.Black),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column {
                    Spacer(modifier = Modifier.height(15.dp))
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.uploadimage3),
                            contentDescription = "pay",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        fontFamily = FontFamily.Cursive,
                        color = Color.White,
                        text = "Select Image",
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(30.dp))


        // Second Card: Make Payments
        Column {
            val mContext = LocalContext.current
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
            ) {
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
                        fontFamily = FontFamily.Cursive,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(30.dp))

        // Third Card: Upload Product
        Column {
            Card(
                modifier = Modifier
                    .width(150.dp)
                    .height(180.dp)
                    .clickable {
                        var productRepository = ProductViewModel(navController, context)
                        productRepository.uploadProduct(name, quantity, price, phone, imageUri!!)
                    },
                colors = CardDefaults.cardColors(Color.Black),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column {
                    Spacer(modifier = Modifier.height(15.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.plussign),
                            contentDescription = "pay",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        color = Color.White,
                        text = "Upload Product",
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }



}


@Composable
@Preview(showBackground = true)
fun AddProductsScreenPreview(){
    AddProductsScreen(navController = rememberNavController())

}


