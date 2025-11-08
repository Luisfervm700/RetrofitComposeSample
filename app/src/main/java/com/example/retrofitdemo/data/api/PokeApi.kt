package com.example.retrofitdemo.data.api

import retrofit2.http.GET

interface PokeApi {
    @GET("pokemon?limit=20")
    suspend fun listPokemon(): PokemonListResponse
}
