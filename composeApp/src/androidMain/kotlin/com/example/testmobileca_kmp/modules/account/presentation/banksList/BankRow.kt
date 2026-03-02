package com.example.testmobileca_kmp.modules.account.presentation.banksList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmobileca_kmp.core.preview.PreviewData
import com.example.testmobileca_kmp.core.theme.AppColors
import com.example.testmobileca_kmp.modules.account.domain.entities.Bank

@Composable
fun BankRow(bank: Bank, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(vertical = 4.dp)
            .background(Color.White)
    ) {
        Text(
            text = bank.name,
            style = MaterialTheme.typography.bodyMedium,
            color = AppColors.bankAccountTitle
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BankRowPreview() {
    BankRow(bank = PreviewData.banks.first())
}
