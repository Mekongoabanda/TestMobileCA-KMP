package com.example.testmobileca_kmp.modules.account.presentation.banksList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmobileca_kmp.R
import com.example.testmobileca_kmp.core.preview.PreviewData
import com.example.testmobileca_kmp.core.theme.AppColors
import com.example.testmobileca_kmp.modules.account.domain.entities.Account
import com.example.testmobileca_kmp.modules.account.domain.entities.Bank
import com.example.testmobileca_kmp.modules.account.presentation.banksList.viewmodels.BanksListState
import com.example.testmobileca_kmp.modules.account.presentation.banksList.viewmodels.BanksListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BanksListScreen(viewModel: BanksListViewModel = koinViewModel(), onAccountClick: (Account) -> Unit) {
    val state by viewModel.viewState.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(AppColors.backgroundScreen)) {
        when (val currentState = state) {
            is BanksListState.Loading -> LoadingContent()
            is BanksListState.Failure ->
                FailureContent(
                    errorMessage = currentState.error,
                    onRetry = { viewModel.fetchBanks() }
                )
            is BanksListState.Success ->
                BanksContent(banks = currentState.banks, onAccountClick = onAccountClick)
        }
    }
}

// region Banks Content

@Composable
private fun BanksContent(banks: List<Bank>, onAccountClick: (Account) -> Unit) {
    val caBanks = banks.filter { it.isCA }
    val otherBanks = banks.filter { !it.isCA }

    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(vertical = 8.dp)) {
        if (caBanks.isNotEmpty()) {
            bankSection(caBanks, isCA = true, onAccountClick = onAccountClick)
        }
        if (otherBanks.isNotEmpty()) {
            bankSection(otherBanks, isCA = false, onAccountClick = onAccountClick)
        }
    }
}

private fun LazyListScope.bankSection(banks: List<Bank>, isCA: Boolean, onAccountClick: (Account) -> Unit) {
    val title = if (isCA) R.string.credit_agricole else R.string.others_banks
    val iconColor = if (isCA) AppColors.caGreen else AppColors.otherBankGray
    val keyPrefix = if (isCA) "ca" else "other"

    item(key = "${keyPrefix}_header") {
        SectionHeader(title = title, iconColor = iconColor)
    }
    banks.forEach { bank ->
        item(key = "${keyPrefix}_bank_${bank.name}") {
            ExpandableBankRow(bank = bank, onAccountClick = onAccountClick)
        }
    }
}

@Composable
private fun SectionHeader(title: Int, iconColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.AccountBalance,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            color = AppColors.sectionHeader
        )
    }
}

@Composable
private fun ExpandableBankRow(bank: Bank, onAccountClick: (Account) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.background(Color.White)) {
        Row(
            modifier =
            Modifier.fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BankRow(bank = bank, modifier = Modifier.weight(1f))
            Icon(
                imageVector =
                if (expanded) {
                    Icons.Outlined.KeyboardArrowUp
                } else {
                    Icons.Outlined.KeyboardArrowDown
                },
                contentDescription = null,
                tint = AppColors.sectionHeader
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column {
                bank.accounts.forEachIndexed { index, account ->
                    AccountRow(
                        account = account,
                        onClick = { onAccountClick(account) },
                        modifier = Modifier.padding(start = 32.dp, end = 16.dp)
                    )
                    if (index < bank.accounts.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.padding(start = 32.dp, end = 16.dp),
                            color = AppColors.divider
                        )
                    }
                }
            }
        }

        HorizontalDivider(color = AppColors.divider)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BanksListSuccessPreview() {
    BanksContent(banks = PreviewData.banks, onAccountClick = {})
}
