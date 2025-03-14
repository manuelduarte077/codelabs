package dev.donmanuel.bottomnavigationcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.donmanuel.bottomnavigationcompose.models.NavItem
import dev.donmanuel.bottomnavigationcompose.navigation.BottomNavBar
import dev.donmanuel.bottomnavigationcompose.navigation.NavItemList
import dev.donmanuel.bottomnavigationcompose.screens.FavoriteScreen
import dev.donmanuel.bottomnavigationcompose.screens.HomeScreen
import dev.donmanuel.bottomnavigationcompose.screens.ProfileScreen
import dev.donmanuel.bottomnavigationcompose.ui.theme.BottomNavigationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavigationComposeTheme {
                BottomNavScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavScreen() {
    var selectedItem by remember { mutableIntStateOf(0) } // State to hold the selected item index

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                navItems = NavItemList.navItemList,
                selectedItem = selectedItem,
                onItemSelected = { index -> selectedItem = index }
            )
        }
    ) {
        ContentScreen(selectedItem)
    }
}

@Composable
fun ContentScreen(selectedItem: Int) {
    when (selectedItem) {
        0 -> HomeScreen()
        1 -> FavoriteScreen()
        2 -> ProfileScreen()
        else -> HomeScreen()
    }
}
