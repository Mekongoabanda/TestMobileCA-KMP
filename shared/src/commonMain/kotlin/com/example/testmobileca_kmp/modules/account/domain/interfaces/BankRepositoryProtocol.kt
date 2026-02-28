package com.example.testmobileca_kmp.modules.account.domain.interfaces

import com.example.testmobileca_kmp.modules.account.domain.entities.Bank

interface BankRepositoryProtocol {
    suspend fun fetchBanks(): Result<List<Bank>>
}
