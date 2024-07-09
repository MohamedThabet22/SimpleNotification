package com.example.notifcation4.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notifcation4.MyViewModel
import com.example.notifcation4.navcation.Screen


@Composable
fun MainScreen(navController: NavController, mainViewModel: MyViewModel = hiltViewModel()) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            mainViewModel.showNotification()
        }) {
            Text(text = "Simple Notification")
        }
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = text, onValueChange = {
            text = it
        } )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            mainViewModel.updateNotifivation(title = "Uploade Notification", name = text)
        }) {
            Text(text = "Update Notification")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {mainViewModel.cancel()}) {
            Text(text = "Cancel Notification")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            navController.navigate(
                Screen.Details.passArgument(message = text
                )
            )
        }) {
            Text(text = "DETAILS SCREEN")
        }

    }
}
