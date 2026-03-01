package com.example.testmobileca_kmp.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme =
    lightColorScheme(
        primary = AppColors.caGreen,
        onPrimary = androidx.compose.ui.graphics.Color.White,
        background = AppColors.backgroundScreen,
        onBackground = AppColors.bankAccountTitle,
        surface = AppColors.cardBackground,
        onSurface = AppColors.bankAccountTitle,
        error = AppColors.errorRed,
        onError = androidx.compose.ui.graphics.Color.White,
        outline = AppColors.divider
    )

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = LightColorScheme, typography = AppTypography, content = content)
}
