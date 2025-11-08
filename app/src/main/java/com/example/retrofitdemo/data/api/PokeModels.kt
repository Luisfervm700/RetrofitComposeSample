package com.example.retrofitdemo.data.api

data class PokemonListResponse(
    val results: List<PokemonSummary>
)

data class PokemonSummary(
    val name: String,
    val url: String
)
