package com.example.testmobileca_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform