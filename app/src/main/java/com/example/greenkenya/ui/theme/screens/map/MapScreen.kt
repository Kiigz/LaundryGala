package com.example.greenkenya.ui.theme.screens.map

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MapScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize()){

        Column(modifier = Modifier
            .fillMaxWidth()
            .height(900.dp)){

            val mUrl = "https://www.google.com/maps/@-1.2610626,36.7962211,14z?entry=ttu&g_ep=EgoyMDI0MDgyMC4xIKXMDSoASAFQAw%3D%3D"

            AndroidView(factory = {
                WebView(it).apply {
                    settings.apply {
                        javaScriptEnabled=true
                    }


                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    loadUrl(mUrl)
                }

            }, update = { it.loadUrl(mUrl) })



        }



    }



}


@Composable
@Preview(showBackground = true)
fun MapScreenPreview(){
    MapScreen(rememberNavController())
}