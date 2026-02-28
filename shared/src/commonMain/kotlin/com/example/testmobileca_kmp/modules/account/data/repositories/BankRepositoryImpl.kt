package com.example.testmobileca_kmp.modules.account.data.repositories

import com.example.testmobileca_kmp.core.network.NetworkManager
import com.example.testmobileca_kmp.modules.account.data.models.BankDTO
import com.example.testmobileca_kmp.modules.account.domain.entities.Bank
import com.example.testmobileca_kmp.modules.account.domain.interfaces.BankRepositoryProtocol
import io.ktor.client.call.body
import io.ktor.client.request.get

class BankRepositoryImpl : BankRepositoryProtocol {

    private val apiUrl = "https://cdf-test-mobile-default-rtdb.europe-west1.firebasedatabase.app/banks.json"

    override suspend fun fetchBanks(): Result<List<Bank>> {
        return try {
            val response: List<BankDTO> = NetworkManager.client.get(apiUrl).body()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            // Note: In KMP, reading a local JSON file from common code requires
            // a specific library (like moko-resources or compose-resources).
            // For now, we return failure to handle it in UI or use a hardcored string fallback.
            Result.failure(e)
        }
    }
}
