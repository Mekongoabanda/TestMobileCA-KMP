package com.example.testmobileca_kmp.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.testmobileca_kmp.R

/** Bottom tab bar destinations — mirrors iOS `ContentView`'s `TabView`. */
enum class AppTab(
        val route: String,
        val labelResId: Int,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector
) {
    MY_ACCOUNTS("myAccounts", R.string.my_accounts, Icons.Filled.Person, Icons.Outlined.Person),
    SIMULATION("simulation", R.string.simulation, Icons.Filled.Star, Icons.Outlined.Star),
    UP_TO_YOU("upToYou", R.string.up_to_you, Icons.Filled.Star, Icons.Outlined.Star);

    companion object {
        /** Returns the [AppTab] matching [route], or null for detail screens. */
        fun fromRoute(route: String?): AppTab? = entries.find { it.route == route }
    }
}
