package dev.donmanuel.datastorepreferencescompose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.donmanuel.datastorepreferencescompose.UserSessionManager
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    userSessionManager: UserSessionManager
) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome to the Home Screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Button(onClick = {
            coroutineScope.launch {
                userSessionManager.setLoggedIn(isLoggedIn = false, keepLoggedIn = false)
                navController.navigate("login") {
                    popUpTo("login") {
                        inclusive = true
                    }
                }
            }
        }) {
            Text(
                text = "Logout",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }

}