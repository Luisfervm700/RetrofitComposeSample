package com.example.retrofitdemo

import com.example.retrofitdemo.data.api.PokeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.test.runTest
import io.strikt.api.expectThat
import io.strikt.assertions.hasSize
import io.strikt.assertions.isEqualTo

class PokeApiTest {

    private lateinit var server: MockWebServer
    private lateinit var api: PokeApi

    @Before
    fun setup() {
        server = MockWebServer()
        server.start()

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        api = Retrofit.Builder()
            .baseUrl(server.url("/").toString())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApi::class.java)
    }

    @After
    fun teardown() {
        server.shutdown()
    }

    @Test
    fun listPokemon_returnsTwo() = runTest {
        val body = """
        {
          "results": [
            {"name": "bulbasaur", "url": "https://pokeapi.co/api/v2/pokemon/1/"},
            {"name": "ivysaur", "url": "https://pokeapi.co/api/v2/pokemon/2/"}
          ]
        }
        """
        server.enqueue(MockResponse().setResponseCode(200).setBody(body))

        val resp = api.listPokemon()

        expectThat(resp.results).hasSize(2)
        expectThat(resp.results[0].name).isEqualTo("bulbasaur")
    }
}
