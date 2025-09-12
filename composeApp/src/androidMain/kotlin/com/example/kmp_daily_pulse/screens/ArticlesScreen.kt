package com.example.kmp_daily_pulse.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.kmp_daily_pulse.articles.Article
import com.example.kmp_daily_pulse.articles.ArticlesViewModel


@Composable
fun ArticlesScreen(articlesViewModel: ArticlesViewModel) {

    val articlesState = articlesViewModel.articlesState.collectAsState()

    Column() {
        AppBar()
        if(articlesState.value.loading) Loader()
        if(articlesState.value.error!=null) Loader() //ErrorMessage(articlesState.value.error!!)
        if(articlesState.value.articles!=null) ArticlesListView(articlesState.value.articles!!)
    }
}

@Composable
fun ArticlesListView(articles: List<Article>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(articles) { article ->
            ArticleItemView(article)
        }
    }
}

@Composable
fun ArticleItemView(article: Article) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ){
        AsyncImage(
            model = article.imgUrl,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = article.des)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
fun Loader(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun ErrorMessage(errorMessage: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = errorMessage)
    }
}

@Composable
fun AppBar(){}