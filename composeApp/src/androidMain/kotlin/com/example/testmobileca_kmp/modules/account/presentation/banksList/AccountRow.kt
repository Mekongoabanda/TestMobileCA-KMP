package com.example.testmobileca_kmp.modules.account.presentation.banksList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmobileca_kmp.core.preview.PreviewData
import com.example.testmobileca_kmp.core.theme.AppColors
import com.example.testmobileca_kmp.core.utils.toCurrencyString
import com.example.testmobileca_kmp.modules.account.domain.entities.Account

@Composable
fun AccountRow(account: Account, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier =
        modifier.fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = account.label,
            style = MaterialTheme.typography.bodyMedium,
            color = AppColors.accountTitle
        )
        Text(
            text = account.balance.toCurrencyString(),
            style = MaterialTheme.typography.bodyMedium,
            color = AppColors.bankAccountAmount
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountRowPreview() {
    AccountRow(account = PreviewData.accounts.first(), onClick = {})
}
