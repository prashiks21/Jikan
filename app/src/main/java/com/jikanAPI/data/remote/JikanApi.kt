package com.jikanAPI.data.remote

import com.jikanAPI.data.remote.dto.AnimeResponse
import com.jikanAPI.data.remote.dto.SingleAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanApi {

    @GET("top/anime")
    suspend fun getTopAnime(): AnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: Int): SingleAnimeResponse
}
