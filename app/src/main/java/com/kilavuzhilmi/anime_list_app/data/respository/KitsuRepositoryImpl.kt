package com.kilavuzhilmi.anime_list_app.data.respository

import com.kilavuzhilmi.anime_list_app.data.network.KitsuApi
import com.kilavuzhilmi.anime_list_app.domain.model.AnimeData
import com.kilavuzhilmi.anime_list_app.domain.repository.KitsuRepository
import com.skydoves.sandwich.onSuccess

/**
 * KitsuRepository arayüzünün somut uygulaması.
 * 
 * Repository Pattern, veri kaynaklarını (API, veritabanı vb.) soyutlamaya yarayan bir tasarım desenidir.
 * Bu sınıf, KitsuApi'yi kullanarak verileri çeker ve uygulama için kullanılabilir hale getirir.
 * Domain katmanı, veri kaynağının detaylarını bilmeden bu repository üzerinden verilere erişir.
 * 
 * @param api KitsuApi enjekte edilir, Retrofit üzerinden API çağrılarını yapmak için kullanılır
 */
class KitsuRepositoryImpl(val api : KitsuApi) : KitsuRepository{
    /**
     * Trend olan animelerin listesini getirir.
     * 
     * Sandwich kütüphanesinin 'onSuccess' metodunu kullanarak sadece başarılı API yanıtlarını işler.
     * API yanıtını domain modellerine dönüştürür (DTO -> Model).
     * 
     * @return Trend olan animelerin listesi veya başarısız olursa boş liste
     */
    override suspend fun getTrendingAnimeList(): List<AnimeData> {
        var animeList : List<AnimeData> = emptyList()
        api.getTrendingAnimeList().onSuccess {
            animeList = data.data.map {
                it.toModel()
            }
        }
        return  animeList
    }

    /**
     * Belirtilen ID'ye sahip animeyi getirir.
     * 
     * @param id Getirilecek anime'nin ID'si
     * @return Anime verisi veya bulunmazsa/hata olursa null
     */
    override suspend fun getAnimeById(id: Int): AnimeData? {
        var anime: AnimeData?= null
        api.getAnimeById(id).onSuccess {
            anime = data.data.toModel()

        }
        return  anime

    }


}