package com.example.testmobileca_kmp.modules.account.presentation.operationsList

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmobileca_kmp.core.preview.PreviewData
import com.example.testmobileca_kmp.core.theme.AppColors
import com.example.testmobileca_kmp.modules.account.domain.entities.Operation

@Composable
fun OperationRow(operation: Operation) {
        Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                                text = operation.title,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = AppColors.bankAccountTitle
                        )
                        Text(
                                text = operation.formattedDate,
                                style = MaterialTheme.typography.bodySmall,
                                color = AppColors.sectionHeader
                        )
                }
                Text(
                        text = operation.amount,
                        style = MaterialTheme.typography.bodyMedium,
                        color = AppColors.operationAmount
                )
        }
}

@Preview(showBackground = true)
@Composable
private fun OperationRowPreview() {
        OperationRow(operation = PreviewData.operations.first())
}
