package dev.donmanuel.cartoonapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite


object NavItemList {

    val navItemList = listOf(
        NavItem(
            label = "2D",
            icon = Icons.AutoMirrored.Filled.List
        ),
        NavItem(
            label = "3D",
            icon = Icons.AutoMirrored.Filled.List
        ),
        NavItem(
            label = "Favorite",
            icon = Icons.Filled.Favorite
        ),
    )

}