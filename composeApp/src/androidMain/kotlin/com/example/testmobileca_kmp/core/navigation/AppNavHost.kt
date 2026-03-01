package com.example.testmobileca_kmp.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testmobileca_kmp.R
import com.example.testmobileca_kmp.core.theme.AppColors
import com.example.testmobileca_kmp.modules.account.domain.entities.Account
import com.example.testmobileca_kmp.modules.account.presentation.banksList.BanksListScreen
import com.example.testmobileca_kmp.modules.account.presentation.operationsList.OperationsListScreen

/**
 * Central navigation host — declares all routes. Mirrors iOS `ContentView`'s per-tab
 * `NavigationStack`.
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    onAccountSelected: (Account) -> Unit,
    getSelectedAccount: () -> Account?
) {
    NavHost(
        navController = navController,
        startDestination = AppTab.MY_ACCOUNTS.route,
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        // Tab 1 — My Accounts
        composable(AppTab.MY_ACCOUNTS.route) {
            BanksListScreen(
                onAccountClick = { account ->
                    onAccountSelected(account)
                    navController.navigate("operationsList")
                }
            )
        }

        // Tab 2 — Simulation (placeholder)
        composable(AppTab.SIMULATION.route) {
            PlaceholderScreen(stringResource(R.string.simulation))
        }

        // Tab 3 — Up to You (placeholder)
        composable(AppTab.UP_TO_YOU.route) { PlaceholderScreen(stringResource(R.string.up_to_you)) }

        // Detail — Operations List (no tab bar)
        composable("operationsList") {
            getSelectedAccount()?.let { account ->
                OperationsListScreen(
                    account = account,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
private fun PlaceholderScreen(title: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = AppColors.sectionHeader
        )
    }
}
