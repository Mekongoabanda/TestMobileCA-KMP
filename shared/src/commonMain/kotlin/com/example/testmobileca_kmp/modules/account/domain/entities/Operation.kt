package com.example.testmobileca_kmp.modules.account.domain.entities

import com.example.testmobileca_kmp.core.extensions.toFormattedDate
import kotlinx.datetime.Instant as KtxInstant

enum class OperationCategory(val value: String) {
        LEISURE("leisure"),
        ONLINE("online"),
        HOUSING("housing"),
        COSTS("costs"),
        FOOD("food"),
        TRIP("trip"),
        UNKNOWN("unknown");

        companion object {
                fun fromString(value: String?): OperationCategory {
                        return entries.find { it.value == value } ?: UNKNOWN
                }
        }
}

data class Operation(
        val id: String,
        val title: String,
        val amount: String,
        val date: KtxInstant,
        val category: OperationCategory
) {
        val formattedDate: String
                get() = date.toFormattedDate()
}
