package com.example.testmobileca_kmp.modules.account.data.models

import com.example.testmobileca_kmp.modules.account.domain.entities.Account
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDTO(
    val id: String,
    val holder: String,
    val role: Int,
    @SerialName("contract_number") val contractNumber: String,
    val label: String,
    @SerialName("product_code") val productCode: String,
    val balance: Double,
    val operations: List<OperationDTO> = emptyList(),
    val order: Int = 0
) {
    fun toDomain(): Account {
        return Account(
            id = id,
            order = order,
            holder = holder,
            role = role,
            contractNumber = contractNumber,
            label = label,
            productCode = productCode,
            balance = balance,
            operations = operations.map { it.toDomain() }
        )
    }
}
