package com.example.kmp_daily_pulse.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kmp_daily_pulse.Platform


@Composable
fun AboutScreen(
    onUpButtonClick: () -> Unit
) {
    Column {
        Toolbar(onUpButtonClick)
        ContentView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(onUpButtonClick: () -> Unit) {
    TopAppBar(
        title = { Text("About Device")},
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Up")
            }
        }
    )
}

@Composable
private fun ContentView() {
    val items = makeItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { row ->
            RowView(title = row.first, subtitle = row.second)
        }
    }

}

@Composable
fun RowView(title: String, subtitle: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(8.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
    platform.logSystemInfo()
    return listOf(
        "OS Name" to "${platform.osName} ${platform.osVersion}",
        "Device Model" to platform.deviceModel,
        "Density" to "${platform.density} dpi"
    )
}
