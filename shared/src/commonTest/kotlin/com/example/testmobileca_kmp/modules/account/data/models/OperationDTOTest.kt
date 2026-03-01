package com.example.testmobileca_kmp.modules.account.data.models

import com.example.testmobileca_kmp.modules.account.domain.entities.OperationCategory
import kotlin.test.Test
import kotlin.test.assertEquals

class OperationDTOTest {

    @Test
    fun `toDomain maps all fields correctly`() {
        val dto =
            OperationDTO(
                id = "op1",
                title = "Netflix",
                amount = "-15,99 €",
                category = "leisure",
                date = "20/02/2026"
            )

        val operation = dto.toDomain()

        assertEquals("op1", operation.id)
        assertEquals("Netflix", operation.title)
        assertEquals("-15,99 €", operation.amount)
        assertEquals("20/02/2026", operation.date)
        assertEquals(OperationCategory.LEISURE, operation.category)
    }

    @Test
    fun `toDomain maps unknown category to UNKNOWN`() {
        val dto =
            OperationDTO(
                id = "op2",
                title = "Divers",
                amount = "-10,00 €",
                category = "something_invalid",
                date = "01/01/2026"
            )

        assertEquals(OperationCategory.UNKNOWN, dto.toDomain().category)
    }
}
