package com.example.testmobileca_kmp.modules.account.domain.entities

data class Bank(val name: String, val isCA: Boolean, val accounts: List<Account>)
