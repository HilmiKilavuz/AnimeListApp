package com.kilavuzhilmi.anime_list_app.domain.repository

import com.kilavuzhilmi.anime_list_app.domain.model.AnimeData

interface KitsuRepository {
    suspend fun getTrendingAnimeList(): List<AnimeData>
    suspend fun getAnimeById(id:Int): AnimeData?
}