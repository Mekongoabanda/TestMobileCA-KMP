package com.example.testmobileca_kmp.core.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.testmobileca_kmp.core.theme.AppColors

/**
 * Bottom navigation bar displaying [AppTab] entries. Mirrors iOS `ContentView`'s `TabView` with
 * `.tabItem`.
 */
@Composable
fun AppBottomBar(currentRoute: String?, onTabSelected: (AppTab) -> Unit) {
    NavigationBar(containerColor = AppColors.cardBackground) {
        AppTab.entries.forEach { tab ->
            val selected = currentRoute == tab.route
            NavigationBarItem(
                    selected = selected,
                    onClick = { if (!selected) onTabSelected(tab) },
                    icon = {
                        Icon(
                                imageVector =
                                        if (selected) tab.selectedIcon else tab.unselectedIcon,
                                contentDescription = stringResource(tab.labelResId)
                        )
                    },
                    label = { Text(stringResource(tab.labelResId)) },
                    colors =
                            NavigationBarItemDefaults.colors(
                                    selectedIconColor = AppColors.caGreen,
                                    selectedTextColor = AppColors.caGreen,
                                    indicatorColor = AppColors.caGreen.copy(alpha = 0.1f)
                            )
            )
        }
    }
}
