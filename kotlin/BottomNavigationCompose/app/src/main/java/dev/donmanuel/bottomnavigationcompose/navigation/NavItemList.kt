package dev.donmanuel.bottomnavigationcompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import dev.donmanuel.bottomnavigationcompose.models.NavItem

object NavItemList {

    val navItemList = listOf(
        NavItem(
            label = "Home",
            icon = Icons.Filled.Home
        ),
        NavItem(
            label = "Favorites",
            icon = Icons.Filled.FavoriteBorder
        ),
        NavItem(
            label = "Profile",
            icon = Icons.Filled.Person
        )
    )
}