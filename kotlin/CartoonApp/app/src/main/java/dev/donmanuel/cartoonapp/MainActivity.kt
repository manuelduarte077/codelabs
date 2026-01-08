package dev.donmanuel.cartoonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import dev.donmanuel.cartoonapp.presentation.navigation.BottomNavBar
import dev.donmanuel.cartoonapp.presentation.navigation.NavItemList
import dev.donmanuel.cartoonapp.presentation.screens.Carton3DListScreen
import dev.donmanuel.cartoonapp.presentation.screens.CartoonListScreen
import dev.donmanuel.cartoonapp.presentation.screens.FavoriteScreen
import dev.donmanuel.cartoonapp.ui.theme.CartoonAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            CartoonAppTheme {
                BottomNavScreen()
            }
        }
    }
}

/**
 * Main screen of the application that contains a bottom navigation bar and displays different
 * content based on the selected item in the navigation bar.
 */

@Composable
fun BottomNavScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navItems = NavItemList.navItemList,
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it }
            )
        }
    ) { paddingValues ->
        ContentScreen(selectedItem, paddingValues)
    }
}

/**
 * Displays the content of the selected screen based on the selected item index.
 * @param selectedItem The index of the selected item in the bottom navigation bar.
 * @param paddingValues The padding values provided by the Scaffold.
 */

@Composable
fun ContentScreen(selectedItem: Int, paddingValues: PaddingValues) {
    when (selectedItem) {
        0 -> CartoonListScreen(modifier = Modifier.padding(paddingValues))
        1 -> Carton3DListScreen(modifier = Modifier.padding(paddingValues))
        2 -> FavoriteScreen(modifier = Modifier.padding(paddingValues))
        else -> CartoonListScreen(modifier = Modifier.padding(paddingValues))
    }
}