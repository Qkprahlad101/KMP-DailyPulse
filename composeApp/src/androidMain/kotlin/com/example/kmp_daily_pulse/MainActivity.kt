package com.example.kmp_daily_pulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kmp_daily_pulse.articles.ArticlesViewModel
import com.example.kmp_daily_pulse.screens.AboutScreen
import com.example.kmp_daily_pulse.screens.ArticlesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val articlesViewModel : ArticlesViewModel by viewModels()
        Platform().logSystemInfo()
        setContent {
            ArticlesScreen(articlesViewModel)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
//    App()
    AboutScreen()
}