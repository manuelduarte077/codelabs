package dev.donmanuel.cartoonapp.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class representing a navigation item in the bottom navigation bar.
 *
 * @param label The label of the navigation item.
 * @param icon The icon of the navigation item.
 */

data class NavItem(
    val label: String,
    val icon: ImageVector,
)