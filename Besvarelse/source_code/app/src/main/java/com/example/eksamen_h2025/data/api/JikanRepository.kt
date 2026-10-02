package com.example.eksamen_h2025.data.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JikanRepository {

    private val _okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()

    private val _retrofit = Retrofit.Builder()
        .client(_okHttpClient)
        .baseUrl("https://api.jikan.moe/v4/")
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    private val _jikanService = _retrofit.create(JikanService::class.java)

    suspend fun getAllAnimes(page: Int): Animes? {
        try {
            val response = _jikanService.getAllAnimes(page)
            return if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d("JikanRepository", e.message.toString())
            return null
        }
    }

    suspend fun getAnimeById(animeId: Int): Anime? {
        return try {
            val response = _jikanService.getAnimeById(animeId)
            if (response.isSuccessful) {
                Log.i("JikanRepository", "Success")
                response.body()?.data
            } else {
                Log.e(
                    "JikanRepository",
                    "API call failed: ${response.code()} ${response.message()}"
                )
                null
            }
        } catch (e: Exception) {
            Log.d("JikanRepository", e.message.toString())
            null
        }
    }

    suspend fun getAnimeByName(name: String): List<Anime>? {
        return try {
            val response = _jikanService.getAnimeByname(name)
            if (response.isSuccessful) {
                response.body()?.data
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d("JikanRepository", e.message.toString())
            null
        }
    }

    suspend fun getRandomAnime(): Anime? {
        return try {
            val response = _jikanService.getRandomAnime()
            if (response.isSuccessful) {
                Log.i("JikanRepository", "Random anime successful")
                response.body()?.data
            } else {
                Log.e(
                    "JikanRepository",
                    "Random anime failed: ${response.code()} ${response.message()}"
                )
                null
            }
        } catch (e: Exception) {
            Log.e("JikanRepository", e.message.toString())
            null
        }
    }
}