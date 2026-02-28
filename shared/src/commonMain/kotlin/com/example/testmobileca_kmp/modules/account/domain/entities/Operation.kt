package com.example.testmobileca_kmp.modules.account.domain.entities

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
        val date: String,
        val category: OperationCategory
)
