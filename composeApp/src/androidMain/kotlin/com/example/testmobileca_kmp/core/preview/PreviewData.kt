package com.example.testmobileca_kmp.core.preview

import com.example.testmobileca_kmp.modules.account.domain.entities.Account
import com.example.testmobileca_kmp.modules.account.domain.entities.Bank
import com.example.testmobileca_kmp.modules.account.domain.entities.Operation
import com.example.testmobileca_kmp.modules.account.domain.entities.OperationCategory

/**
 * Centralized sample data for Compose @Preview functions. Keeps preview data DRY across all
 * composables.
 */
object PreviewData {
    val operations =
            listOf(
                    Operation(
                            "1",
                            "Prélèvement Netflix",
                            "-15,99 €",
                            "20/02/2026",
                            OperationCategory.LEISURE
                    ),
                    Operation(
                            "2",
                            "Virement salaire",
                            "+2 450,00 €",
                            "01/02/2026",
                            OperationCategory.UNKNOWN
                    ),
                    Operation(
                            "3",
                            "Courses Carrefour",
                            "-85,32 €",
                            "18/02/2026",
                            OperationCategory.FOOD
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
