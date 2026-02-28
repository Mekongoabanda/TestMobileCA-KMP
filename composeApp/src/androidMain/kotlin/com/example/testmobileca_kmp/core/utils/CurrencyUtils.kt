package com.example.testmobileca_kmp.core.utils

import java.text.NumberFormat
import java.util.Locale

/**
 * Formats a [Double] as a currency string using the French locale (€).
 *
 * Example: `2031.84.toCurrencyString()` → `"2 031,84 €"`
 */
fun Double.toCurrencyString(locale: Locale = Locale.FRANCE): String {
    return NumberFormat.getCurrencyInstance(locale).format(this)
}
