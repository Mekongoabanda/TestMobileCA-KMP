package com.example.testmobileca_kmp.modules.account.domain.usecases

import com.example.testmobileca_kmp.modules.account.domain.entities.Account
import com.example.testmobileca_kmp.modules.account.domain.entities.Bank
import com.example.testmobileca_kmp.modules.account.domain.entities.Operation
import com.example.testmobileca_kmp.modules.account.domain.entities.OperationCategory
import com.example.testmobileca_kmp.modules.account.domain.interfaces.BankRepositoryProtocol
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

/**
 * Fake implementation of [BankRepositoryProtocol] for unit testing. Returns a configurable [Result]
 * without network calls.
 */
private class FakeBankRepository(private val result: Result<List<Bank>>) : BankRepositoryProtocol {
    override suspend fun fetchBanks(): Result<List<Bank>> = result
}

class GetSortedBanksUseCaseTest {

    // region Test Data

    private val opB =
        Operation("1", "Achat Boulangerie", "-3,50 €", "15/02/2026", OperationCategory.FOOD)
    private val opA = Operation("2", "Netflix", "-15,99 €", "20/02/2026", OperationCategory.LEISURE)
    private val opC = Operation("3", "Spotify", "-9,99 €", "20/02/2026", OperationCategory.LEISURE)

    private val accountZ =
        Account("1", 1, "Yannick", 1, "111", "Livret A", "02", 15000.0, listOf(opB, opA))
    private val accountA =
        Account("2", 2, "Yannick", 1, "222", "Compte courant", "01", 2031.84, listOf(opC))

    private val bnp = Bank("BNP Paribas", false, listOf(accountZ, accountA))
    private val ca = Bank("Crédit Agricole", true, listOf(accountZ))

    // endregion

    @Test
    fun `CA banks are sorted before other banks`() = runTest {
        val useCase = GetSortedBanksUseCase(FakeBankRepository(Result.success(listOf(bnp, ca))))

        val result = useCase.execute()
        val banks = result.getOrThrow()

        assertEquals(2, banks.size)
        assertTrue(banks[0].isCA, "First bank should be CA")
        assertEquals("Crédit Agricole", banks[0].name)
        assertEquals("BNP Paribas", banks[1].name)
    }

    @Test
    fun `accounts are sorted alphabetically by label`() = runTest {
        val useCase = GetSortedBanksUseCase(FakeBankRepository(Result.success(listOf(bnp))))

        val result = useCase.execute()
        val accounts = result.getOrThrow().first().accounts

        assertEquals("Compte courant", accounts[0].label)
        assertEquals("Livret A", accounts[1].label)
    }

    @Test
    fun `operations are sorted by date descending then title ascending`() = runTest {
        // accountZ has: opB (15/02) and opA (20/02)
        // Expected after sort: opA (20/02) before opB (15/02) — date desc
        val useCase = GetSortedBanksUseCase(FakeBankRepository(Result.success(listOf(ca))))

        val result = useCase.execute()
        val operations = result.getOrThrow().first().accounts.first().operations

        assertEquals("Netflix", operations[0].title) // 20/02 > 15/02
        assertEquals("Achat Boulangerie", operations[1].title)
    }

    @Test
    fun `operations with same date are sorted by title ascending`() = runTest {
        // opA (Netflix, 20/02) and opC (Spotify, 20/02) — same date
        val account = Account("1", 1, "Y", 1, "111", "Compte", "01", 100.0, listOf(opC, opA))
        val bank = Bank("Test", true, listOf(account))
        val useCase = GetSortedBanksUseCase(FakeBankRepository(Result.success(listOf(bank))))

        val result = useCase.execute()
        val operations = result.getOrThrow().first().accounts.first().operations

        assertEquals("Netflix", operations[0].title) // N < S alphabetically
        assertEquals("Spotify", operations[1].title)
    }

    @Test
    fun `repository failure is propagated`() = runTest {
        val error = RuntimeException("Network error")
        val useCase = GetSortedBanksUseCase(FakeBankRepository(Result.failure(error)))

        val result = useCase.execute()

        assertTrue(result.isFailure)
        assertEquals("Network error", result.exceptionOrNull()?.message)
    }

    @Test
    fun `empty bank list returns empty success`() = runTest {
        val useCase = GetSortedBanksUseCase(FakeBankRepository(Result.success(emptyList())))

        val result = useCase.execute()

        assertTrue(result.isSuccess)
        assertTrue(result.getOrThrow().isEmpty())
    }
}
