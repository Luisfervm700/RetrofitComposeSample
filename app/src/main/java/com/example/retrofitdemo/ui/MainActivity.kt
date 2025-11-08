package com.example.retrofitdemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofitdemo.data.PokemonRepository
import com.example.retrofitdemo.data.api.PokemonSummary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen() {
    val repo = remember { PokemonRepository() }
    var items by remember { mutableStateOf<List<PokemonSummary>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            items = repo.getFirstTwenty()
        } catch (t: Throwable) {
            error = t.message
        } finally {
            isLoading = false
        }
    }

    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Retrofit + PokeAPI") }) }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                isLoading -> {
                    Column(Modifier.padding(16.dp)) {
                        Text("Cargando...")
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                }
                error != null -> {
                    Text("Error: $error", modifier = Modifier.padding(16.dp))
                }
                else -> {
                    PokemonList(items = items)
                }
            }
        }
    }
}

@Composable
fun PokemonList(items: List<PokemonSummary>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { p ->
            ElevatedCard {
                Column(Modifier.padding(16.dp)) {
                    Text(text = p.name.replaceFirstChar { it.uppercase() }, style = MaterialTheme.typography.titleMedium)
                    Text(text = p.url, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
