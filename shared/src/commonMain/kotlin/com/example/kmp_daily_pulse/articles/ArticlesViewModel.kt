package com.example.kmp_daily_pulse.articles

import com.example.kmp_daily_pulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel: BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    private val mockArticles = listOf(
        Article(
            "Title1",
            "Desc1",
            "1-1-10",
            "https://images.unsplash.com/photo-1624555130581-1d9cca783bc0?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8dXJsfGVufDB8fDB8fHww"
        ),
        Article(
            "Title1",
            "Desc1",
            "2-1-10",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQE_BDtCMphYPuPZgBgJjD4zdEKiV46dLJCyg&s"
        ),
        Article(
            "Title1",
            "Desc1",
            "3-1-10",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBcm_jpu_AoJ5QjNIsGe-P8dwIHpZ8EmS5zA&s"
        ),
    )

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            val fetchedArticles = fetchArticles()
            delay(1000)
            _articlesState.emit(ArticlesState(articles = _articlesState.value.articles, error = "Error"))
            delay(1000)
            _articlesState.emit(ArticlesState(articles = fetchedArticles))
        }
    }

    suspend private fun fetchArticles() : List<Article> {
        println("PRAHLAD: mockArticles: $mockArticles")
        return mockArticles
    }

}