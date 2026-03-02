package com.example.testmobileca_kmp.modules.account.presentation.operationsList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmobileca_kmp.R
import com.example.testmobileca_kmp.core.preview.PreviewData
import com.example.testmobileca_kmp.core.theme.AppColors
import com.example.testmobileca_kmp.core.utils.toCurrencyString
import com.example.testmobileca_kmp.modules.account.domain.entities.Account

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperationsListScreen(account: Account, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.my_accounts)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.my_accounts)
                        )
                    }
                },
                colors =
                TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColors.backgroundScreen
                )
            )
        },
        containerColor = AppColors.backgroundScreen
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Balance
            Text(
                text = account.balance.toCurrencyString(),
                style = MaterialTheme.typography.headlineLarge,
                color = AppColors.bankAccountAmount,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Account label
            Text(
                text = account.label,
                style = MaterialTheme.typography.titleLarge,
                color = AppColors.bankAccountTitle,
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
            )

            // Operations list
            if (account.operations.isEmpty()) {
                EmptyOperationsMessage()
            } else {
                OperationsList(account)
            }
        }
    }
}

@Composable
private fun EmptyOperationsMessage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Aucune opération",
            style = MaterialTheme.typography.bodyMedium,
            color = AppColors.sectionHeader,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun OperationsList(account: Account) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(
            count = account.operations.size,
            key = { index -> "${index}_${account.operations[index].id}" }
        ) { index ->
            OperationRow(operation = account.operations[index])
            HorizontalDivider(color = AppColors.divider)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OperationsListScreenPreview() {
    OperationsListScreen(account = PreviewData.accounts.first(), onBackClick = {})
}
