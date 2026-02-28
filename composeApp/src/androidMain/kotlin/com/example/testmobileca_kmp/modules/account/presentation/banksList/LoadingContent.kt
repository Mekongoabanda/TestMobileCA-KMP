package com.example.testmobileca_kmp.modules.account.presentation.banksList

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmobileca_kmp.R
import com.example.testmobileca_kmp.core.theme.AppColors

@Composable
fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = AppColors.caGreen)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                    text = stringResource(R.string.loading_bank),
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.sectionHeader
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoadingContentPreview() {
    LoadingContent()
}
