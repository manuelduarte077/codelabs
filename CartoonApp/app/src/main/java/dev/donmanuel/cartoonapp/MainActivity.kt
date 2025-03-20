package dev.donmanuel.cartoonapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
        setContent {
            CartoonAppTheme {
                BottomNavScreen()
            }
        }
    }
}


@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun BottomNavScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                navItems = NavItemList.navItemList,
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it }
            )
        }
    ) {
        ContentScreen(selectedItem)
    }
}

@Composable
fun ContentScreen(selectedItem: Int) {
    when (selectedItem) {
        0 -> CartoonListScreen()
        1 -> Carton3DListScreen()
        2 -> FavoriteScreen()
        else -> CartoonListScreen()
    }
}