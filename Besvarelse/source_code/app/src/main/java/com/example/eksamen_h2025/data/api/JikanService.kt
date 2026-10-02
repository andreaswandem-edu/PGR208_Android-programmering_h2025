package com.example.eksamen_h2025.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanService {

    // Hente alle karakterer
    @GET("anime")
    suspend fun getAllAnimes(
        @Query("page") page: Int
    ): Response<Animes>

    // Hente karakter etter id
    @GET("anime/{id}")
    suspend fun getAnimeById(
        @Path("id") animeId: Int
    ): Response<AnimeResponse>

    // Ekstra funksjon for å søke på navn
    @GET("anime")
    suspend fun getAnimeByname(
        @Query("q") name: String

    ): Response<Animes>

    // Henter random anime
    @GET("random/anime")
    suspend fun getRandomAnime(): Response<AnimeResponse>
}