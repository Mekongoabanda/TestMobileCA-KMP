package com.example.testmobileca_kmp.modules.account.data.models

import com.example.testmobileca_kmp.modules.account.domain.entities.OperationCategory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.datetime.Instant as KtxInstant

class OperationDTOTest {

    @Test
    fun `toDomain maps all fields correctly`() {
        val dto =
            OperationDTO(
                id = "op1",
                title = "Netflix",
                amount = "-15,99 €",
                category = "leisure",
                date = "1644870724"
            )

        val operation = dto.toDomain()

        assertEquals("op1", operation.id)
        assertEquals("Netflix", operation.title)
        assertEquals("-15,99 €", operation.amount)
        assertEquals(KtxInstant.fromEpochSeconds(1644870724), operation.date)
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
                date = "1644870724"
            )

        assertEquals(OperationCategory.UNKNOWN, dto.toDomain().category)
    }

    @Test
    fun `toDomain handles invalid date gracefully`() {
        val dto =
            OperationDTO(
                id = "op3",
                title = "Test",
                amount = "-1,00 €",
                category = "costs",
                date = "invalid_date"
            )

        val operation = dto.toDomain()
        assertEquals(KtxInstant.fromEpochSeconds(0), operation.date)
    }
}
