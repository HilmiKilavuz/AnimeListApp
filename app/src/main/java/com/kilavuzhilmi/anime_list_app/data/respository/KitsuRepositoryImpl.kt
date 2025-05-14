package com.kilavuzhilmi.anime_list_app.data.respository

import com.kilavuzhilmi.anime_list_app.data.network.KitsuApi
import com.kilavuzhilmi.anime_list_app.domain.model.AnimeData
import com.kilavuzhilmi.anime_list_app.domain.repository.KitsuRepository
import com.skydoves.sandwich.onSuccess

class KitsuRepositoryImpl(val api : KitsuApi) : KitsuRepository{
    override suspend fun getTrendingAnimeList(): List<AnimeData> {
        var animeList : List<AnimeData> = emptyList()
        api.getTrendingAnimeList().onSuccess {
            animeList = data.data.map {
                it.toModel()
            }
        }
        return  animeList
    }

    override suspend fun getAnimeById(id: Int): AnimeData? {
        var anime: AnimeData?= null
        api.getAnimeById(id).onSuccess {
            anime = data.data.toModel()

        }
        return  anime

    }


}