package com.example.testmobileca_kmp.modules.account.domain.usecases

import com.example.testmobileca_kmp.modules.account.domain.entities.Bank
import com.example.testmobileca_kmp.modules.account.domain.entities.Operation
import com.example.testmobileca_kmp.modules.account.domain.interfaces.BankRepositoryProtocol

class GetSortedBanksUseCase(private val repository: BankRepositoryProtocol) {

    suspend fun execute(): Result<List<Bank>> {
        val result = repository.fetchBanks()
        if (result.isFailure) return result

        val banks = result.getOrNull() ?: emptyList()

        val sortedBanks =
            banks
                .map { bank ->
                    val sortedAccounts =
                        bank.accounts
                            .map { account ->
                                val sortedOperations =
                                    account.operations.sortedWith(
                                        compareByDescending<Operation> {
                                            it.date
                                        }
                                            .thenBy { it.title }
                                    )
                                account.copy(operations = sortedOperations)
                            }
                            .sortedBy { it.label }

                    bank.copy(accounts = sortedAccounts)
                }
                .sortedWith(compareByDescending<Bank> { it.isCA }.thenBy { it.name })

        return Result.success(sortedBanks)
    }
}
