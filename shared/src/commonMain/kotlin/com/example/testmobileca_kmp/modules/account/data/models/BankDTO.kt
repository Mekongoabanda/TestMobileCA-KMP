package com.example.testmobileca_kmp.modules.account.data.models

import com.example.testmobileca_kmp.modules.account.domain.entities.Bank
import kotlinx.serialization.Serializable

@Serializable
data class BankDTO(val name: String, val isCA: Int, val accounts: List<AccountDTO>) {
    fun toDomain(): Bank {
        return Bank(name = name, isCA = isCA == 1, accounts = accounts.map { it.toDomain() })
    }
}
