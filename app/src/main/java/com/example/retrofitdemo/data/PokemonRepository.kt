package com.example.retrofitdemo.data

import com.example.retrofitdemo.data.api.PokemonSummary
import com.example.retrofitdemo.data.api.RetrofitProvider

class PokemonRepository {
    suspend fun getFirstTwenty(): List<PokemonSummary> {
        return RetrofitProvider.api.listPokemon().results
    }
}
