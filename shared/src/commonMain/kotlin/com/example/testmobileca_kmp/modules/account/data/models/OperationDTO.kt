package com.example.testmobileca_kmp.modules.account.data.models

import com.example.testmobileca_kmp.core.extensions.toInstant
import com.example.testmobileca_kmp.modules.account.domain.entities.Operation
import com.example.testmobileca_kmp.modules.account.domain.entities.OperationCategory
import kotlinx.serialization.Serializable

@Serializable
data class OperationDTO(
    val id: String,
    val title: String,
    val amount: String,
    val category: String,
    val date: String
) {
    fun toDomain(): Operation {
        return Operation(
            id = id,
            title = title,
            amount = amount,
            date = date.toInstant(),
            category = OperationCategory.fromString(category)
        )
    }
}
