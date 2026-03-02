package com.example.testmobileca_kmp.core.extensions

import kotlinx.datetime.Instant as KtxInstant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun String.toInstant(): KtxInstant {
    val epoch = this.toLongOrNull() ?: 0L
    return KtxInstant.fromEpochSeconds(epoch)
}

fun KtxInstant.toFormattedDate(): String {
    val local = this.toLocalDateTime(TimeZone.currentSystemDefault())
    val day = local.dayOfMonth.toString().padStart(2, '0')
    val month = local.monthNumber.toString().padStart(2, '0')
    val year = local.year
    return "$day/$month/$year"
}
