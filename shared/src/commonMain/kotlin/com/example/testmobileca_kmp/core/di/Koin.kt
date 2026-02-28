package com.example.testmobileca_kmp.core.di

import com.example.testmobileca_kmp.modules.account.data.repositories.BankRepositoryImpl
import com.example.testmobileca_kmp.modules.account.domain.interfaces.BankRepositoryProtocol
import com.example.testmobileca_kmp.modules.account.domain.usecases.GetSortedBanksUseCase
import com.example.testmobileca_kmp.modules.account.presentation.banksList.viewmodels.BanksListViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val sharedModule = module {
    single<BankRepositoryProtocol> { BankRepositoryImpl() }
    factory { GetSortedBanksUseCase(get()) }
    factory { BanksListViewModel(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(sharedModule)
}

// iOS entry point
fun initKoin() = initKoin {}
