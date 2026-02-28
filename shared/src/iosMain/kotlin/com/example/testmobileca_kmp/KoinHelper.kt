package com.example.testmobileca_kmp

import com.example.testmobileca_kmp.modules.account.presentation.banksList.viewmodels.BanksListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class KoinHelper : KoinComponent {
    fun getBanksListViewModel(): BanksListViewModel = get()
}
