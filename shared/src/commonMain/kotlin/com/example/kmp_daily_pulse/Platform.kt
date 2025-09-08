package com.example.kmp_daily_pulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform