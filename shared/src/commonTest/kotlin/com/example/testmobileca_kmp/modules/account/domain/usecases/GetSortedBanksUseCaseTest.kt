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
            Operation(
                    id = "1",
                    title = "Achat Boulangerie",
                    amount = "-3,50 €",
                    date = KtxInstant.fromEpochSeconds(1644870000),
                    category = OperationCategory.FOOD
            )
    private val opA =
            Operation(
                    id = "2",
                    title = "Netflix",
                    amount = "-15,99 €",
                    date = KtxInstant.fromEpochSeconds(1645300000),
                    category = OperationCategory.LEISURE
            )
    private val opC =
            Operation(
                    id = "3",
                    title = "Spotify",
                    amount = "-9,99 €",
                    date = KtxInstant.fromEpochSeconds(1645300000),
                    category = OperationCategory.LEISURE
            )

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
        // opB (epoch 1644870000) < opA (epoch 1645300000)
        // Expected: opA first (more recent)
        val useCase = GetSortedBanksUseCase(FakeBankRepository(Result.success(listOf(ca))))

        val result = useCase.execute()
        val operations = result.getOrThrow().first().accounts.first().operations

        assertEquals("Netflix", operations[0].title)
        assertEquals("Achat Boulangerie", operations[1].title)
    }

    @Test
    fun `operations with same date are sorted by title ascending`() = runTest {
        // opA (Netflix) and opC (Spotify) — same Instant
        val account = Account("1", 1, "Y", 1, "111", "Compte", "01", 100.0, listOf(opC, opA))
        val bank = Bank("Test", true, listOf(account))
        val useCase = GetSortedBanksUseCase(FakeBankRepository(Result.success(listOf(bank))))

        val result = useCase.execute()
        val operations = result.getOrThrow().first().accounts.first().operations

        assertEquals("Netflix", operations[0].title) // N < S
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
