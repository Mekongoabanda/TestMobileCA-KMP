package com.example.testmobileca_kmp.modules.account.domain.entities

import kotlin.test.Test
import kotlin.test.assertEquals

class OperationCategoryTest {

    @Test
    fun `fromString returns correct category for known values`() {
        assertEquals(OperationCategory.LEISURE, OperationCategory.fromString("leisure"))
        assertEquals(OperationCategory.ONLINE, OperationCategory.fromString("online"))
        assertEquals(OperationCategory.HOUSING, OperationCategory.fromString("housing"))
        assertEquals(OperationCategory.COSTS, OperationCategory.fromString("costs"))
        assertEquals(OperationCategory.FOOD, OperationCategory.fromString("food"))
        assertEquals(OperationCategory.TRIP, OperationCategory.fromString("trip"))
    }

    @Test
    fun `fromString returns UNKNOWN for invalid value`() {
        assertEquals(OperationCategory.UNKNOWN, OperationCategory.fromString("invalid"))
        assertEquals(OperationCategory.UNKNOWN, OperationCategory.fromString(""))
    }

    @Test
    fun `fromString returns UNKNOWN for null value`() {
        assertEquals(OperationCategory.UNKNOWN, OperationCategory.fromString(null))
    }

    @Test
    fun `enum value property matches expected string`() {
        assertEquals("leisure", OperationCategory.LEISURE.value)
        assertEquals("unknown", OperationCategory.UNKNOWN.value)
    }
}
