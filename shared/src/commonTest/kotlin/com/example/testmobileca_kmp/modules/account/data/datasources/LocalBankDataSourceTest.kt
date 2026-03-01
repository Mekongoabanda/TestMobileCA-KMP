package com.example.testmobileca_kmp.modules.account.data.datasources

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocalBankDataSourceTest {

    @Test
    fun `fetchBanks returns success with 4 banks`() {
        val result = LocalBankDataSource.fetchBanks()

        assertTrue(result.isSuccess)
        assertEquals(4, result.getOrThrow().size)
    }

    @Test
    fun `fetchBanks contains CA banks`() {
        val banks = LocalBankDataSource.fetchBanks().getOrThrow()
        val caBanks = banks.filter { it.isCA }

        assertEquals(2, caBanks.size)
        assertTrue(caBanks.any { it.name == "CA Languedoc" })
        assertTrue(caBanks.any { it.name == "CA Centre-Est" })
    }

    @Test
    fun `fetchBanks contains other banks`() {
        val banks = LocalBankDataSource.fetchBanks().getOrThrow()
        val otherBanks = banks.filter { !it.isCA }

        assertEquals(2, otherBanks.size)
        assertTrue(otherBanks.any { it.name == "Boursorama" })
        assertTrue(otherBanks.any { it.name == "Banque Pop" })
    }

    @Test
    fun `fetchBanks maps accounts correctly`() {
        val banks = LocalBankDataSource.fetchBanks().getOrThrow()
        val caLanguedoc = banks.first { it.name == "CA Languedoc" }

        assertEquals(3, caLanguedoc.accounts.size)
        assertEquals("Compte de dépôt", caLanguedoc.accounts.first().label)
        assertEquals(2031.84, caLanguedoc.accounts.first().balance)
    }

    @Test
    fun `fetchBanks maps operations correctly`() {
        val banks = LocalBankDataSource.fetchBanks().getOrThrow()
        val firstAccount = banks.first { it.name == "CA Languedoc" }.accounts.first()

        assertEquals(2, firstAccount.operations.size)
        assertEquals("Prélèvement Netflix", firstAccount.operations.first().title)
    }
}
