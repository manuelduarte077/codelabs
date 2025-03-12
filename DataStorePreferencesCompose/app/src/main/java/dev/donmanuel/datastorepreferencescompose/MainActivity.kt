package dev.donmanuel.datastorepreferencescompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.donmanuel.datastorepreferencescompose.screens.HomeScreen
import dev.donmanuel.datastorepreferencescompose.screens.LoginScreen
import dev.donmanuel.datastorepreferencescompose.ui.theme.DataStorePreferencesComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataStorePreferencesComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    val userSessionManager = UserSessionManager(this)
                    val navController = rememberNavController()
                    var isLoggedIn by remember { mutableStateOf(false) }

                    LaunchedEffect(Unit) {
                        userSessionManager.getLoggedInStatus().collect {
                            isLoggedIn = it
                        }
                    }

                    if (isLoggedIn) {
                        navController.navigate("home") {
                            popUpTo("home") {
                                inclusive = true
                            }
                        }
                    }

                    NavHost(
                        navController = navController,
                        startDestination = "login",
                    ) {
                        composable("login") {
                            LoginScreen(
                                navController = navController,
                                userSessionManager = userSessionManager
                            )
                        }

                        composable("home") {
                            HomeScreen(
                                navController = navController,
                                userSessionManager = userSessionManager
                            )
                        }
                    }

                }
            }
        }
    }
}

