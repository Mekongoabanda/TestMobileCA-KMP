package com.example.testmobileca_kmp.core.preview

import com.example.testmobileca_kmp.modules.account.domain.entities.Account
import com.example.testmobileca_kmp.modules.account.domain.entities.Bank
import com.example.testmobileca_kmp.modules.account.domain.entities.Operation
import com.example.testmobileca_kmp.modules.account.domain.entities.OperationCategory
import kotlinx.datetime.Instant as KtxInstant

/**
 * Centralized sample data for Compose @Preview functions. Keeps preview data DRY across all
 * composables.
 */
object PreviewData {
    val operations =
            listOf(
                    Operation(
                            id = "1",
                            title = "Prélèvement Netflix",
                            amount = "-15,99 €",
                            date = KtxInstant.fromEpochSeconds(1644870724),
                            category = OperationCategory.LEISURE
                    ),
                    Operation(
                            id = "2",
                            title = "Virement salaire",
                            amount = "+2 450,00 €",
                            date = KtxInstant.fromEpochSeconds(1643673600),
                            category = OperationCategory.UNKNOWN
                    ),
                    Operation(
                            id = "3",
                            title = "Courses Carrefour",
                            amount = "-85,32 €",
                            date = KtxInstant.fromEpochSeconds(1645142400),
                            category = OperationCategory.FOOD
                    )
            )

    val accounts =
            listOf(
                    Account(
                            "1",
                            1,
                            "Yannick",
                            1,
                            "123456789",
                            "Compte courant",
                            "01",
                            2031.84,
                            operations
                    ),
                    Account(
                            "2",
                            2,
                            "Yannick",
                            1,
                            "987654321",
                            "Livret A",
                            "02",
                            15000.0,
                            emptyList()
                    )
            )

    val banks =
            listOf(
                    Bank("Crédit Agricole Île-de-France", true, accounts),
                    Bank("BNP Paribas", false, listOf(accounts.first()))
            )
}
