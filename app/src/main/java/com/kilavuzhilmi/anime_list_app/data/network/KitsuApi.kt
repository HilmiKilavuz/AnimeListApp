package com.kilavuzhilmi.anime_list_app.data.network

import com.kilavuzhilmi.anime_list_app.data.network.dto.AnimeResponseDto
import com.kilavuzhilmi.anime_list_app.data.network.dto.TrendingAnimeListDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface KitsuApi {
    @GET("trending/anime")
    suspend fun getTrendingAnimeList(): ApiResponse<TrendingAnimeListDto>

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): ApiResponse<AnimeResponseDto>

    companion object{
        const val baseUrl = "https://kitsu.io/api/edge/"
    }

}