package com.example.testmobileca_kmp

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testmobileca_kmp.core.navigation.AppBottomBar
import com.example.testmobileca_kmp.core.navigation.AppNavHost
import com.example.testmobileca_kmp.core.navigation.AppTab
import com.example.testmobileca_kmp.core.theme.AppColors
import com.example.testmobileca_kmp.core.theme.AppTheme
import com.example.testmobileca_kmp.modules.account.domain.entities.Account

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        val currentTab = AppTab.fromRoute(currentRoute)

        // Hold selected account for detail navigation
        var selectedAccount by remember { mutableStateOf<Account?>(null) }

        Scaffold(
            topBar = {
                if (currentTab != null) {
                    TopAppBar(
                        title = {
                            Text(
                                text = stringResource(currentTab.labelResId),
                                style = MaterialTheme.typography.headlineMedium
                            )
                        },
                        colors =
                        TopAppBarDefaults.topAppBarColors(
                            containerColor = AppColors.backgroundScreen
                        )
                    )
                }
            },
            bottomBar = {
                if (currentTab != null) {
                    AppBottomBar(currentRoute = currentRoute) { tab ->
                        navController.navigate(tab.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            },
            containerColor = AppColors.backgroundScreen
        ) { paddingValues ->
            AppNavHost(
                navController = navController,
                paddingValues = paddingValues,
                onAccountSelected = { selectedAccount = it },
                getSelectedAccount = { selectedAccount }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}
