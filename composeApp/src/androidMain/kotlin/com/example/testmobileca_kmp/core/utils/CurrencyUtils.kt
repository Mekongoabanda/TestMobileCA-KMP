package com.example.testmobileca_kmp.core.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.toCurrencyString(locale: Locale = Locale.FRANCE): String {
    return NumberFormat.getCurrencyInstance(locale).format(this)
}
