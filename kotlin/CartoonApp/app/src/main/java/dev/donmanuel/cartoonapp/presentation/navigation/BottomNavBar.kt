package dev.donmanuel.cartoonapp.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * Bottom navigation bar for the app.
 */

@Composable
fun BottomNavBar(
    navItems: List<NavItem>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
) {
    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { onItemSelected(index) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                    )
                },
                label = {
                    Text(text = item.label)
                },
            )
        }
    }
}