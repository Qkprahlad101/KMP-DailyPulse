package com.example.kmp_daily_pulse.articles

data class ArticlesState(
    val articles: List<Article>? = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)