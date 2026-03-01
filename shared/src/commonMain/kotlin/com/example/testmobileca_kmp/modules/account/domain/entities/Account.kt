package com.example.testmobileca_kmp.modules.account.domain.entities

data class Account(
    val id: String,
    val order: Int,
    val holder: String,
    val role: Int,
    val contractNumber: String,
    val label: String,
    val productCode: String,
    val balance: Double,
    val operations: List<Operation>
)
