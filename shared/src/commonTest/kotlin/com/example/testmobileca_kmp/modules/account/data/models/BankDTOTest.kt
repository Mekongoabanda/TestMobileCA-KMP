package com.example.testmobileca_kmp.modules.account.data.models

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BankDTOTest {

    private val sampleOperationDTO =
        OperationDTO(
            id = "op1",
            title = "Netflix",
            amount = "-15,99 €",
            category = "leisure",
            date = "20/02/2026"
        )

    private val sampleAccountDTO =
        AccountDTO(
            id = "acc1",
            holder = "Yannick",
            role = 1,
            contractNumber = "123456789",
            label = "Compte courant",
            productCode = "01",
            balance = 2031.84,
            operations = listOf(sampleOperationDTO),
            order = 1
        )

    @Test
    fun `toDomain maps isCA 1 to true`() {
        val dto = BankDTO(name = "Crédit Agricole", isCA = 1, accounts = emptyList())
        assertTrue(dto.toDomain().isCA)
    }

    @Test
    fun `toDomain maps isCA 0 to false`() {
        val dto = BankDTO(name = "BNP Paribas", isCA = 0, accounts = emptyList())
        assertFalse(dto.toDomain().isCA)
    }

    @Test
    fun `toDomain maps bank name correctly`() {
        val dto = BankDTO(name = "Crédit Agricole IDF", isCA = 1, accounts = emptyList())
        assertEquals("Crédit Agricole IDF", dto.toDomain().name)
    }

    @Test
    fun `toDomain maps nested accounts and operations`() {
        val dto = BankDTO(name = "CA", isCA = 1, accounts = listOf(sampleAccountDTO))

        val bank = dto.toDomain()

        assertEquals(1, bank.accounts.size)
        val account = bank.accounts.first()
        assertEquals("acc1", account.id)
        assertEquals("Compte courant", account.label)
        assertEquals(2031.84, account.balance)
        assertEquals(1, account.operations.size)
        assertEquals("Netflix", account.operations.first().title)
    }

    @Test
    fun `toDomain handles empty accounts list`() {
        val dto = BankDTO(name = "Empty Bank", isCA = 0, accounts = emptyList())
        assertTrue(dto.toDomain().accounts.isEmpty())
    }
}
