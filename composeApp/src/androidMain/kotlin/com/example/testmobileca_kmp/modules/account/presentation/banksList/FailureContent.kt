package com.example.testmobileca_kmp.modules.account.presentation.banksList

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmobileca_kmp.R
import com.example.testmobileca_kmp.core.theme.AppColors

/** Error state with retry button — shown when bank fetching fails. */
@Composable
fun FailureContent(errorMessage: String, onRetry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Warning,
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = AppColors.errorRed
            )
            Text(
                text = stringResource(R.string.unable_to_read_data),
                style = MaterialTheme.typography.titleMedium,
                color = AppColors.bankAccountTitle
            )
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.sectionHeader
            )
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.caGreen)
            ) { Text(stringResource(R.string.retry)) }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FailureContentPreview() {
    FailureContent(errorMessage = "Network error: timeout", onRetry = {})
}
