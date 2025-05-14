package com.kilavuzhilmi.anime_list_app.domain.repository

import com.kilavuzhilmi.anime_list_app.domain.model.AnimeData

/**
 * KitsuRepository Interface - Anime verilerine erişim için repository arayüzü
 * 
 * Repository Pattern, uygulama ile veri kaynakları arasında bir soyutlama katmanı oluşturur.
 * Bu interface, veri kaynaklarına (API, veritabanı, vb.) erişim için gereken operasyonları tanımlar.
 * 
 * Clean Architecture'ın bir parçası olarak, bu interface domain katmanında bulunur ve
 * uygulamanın diğer katmanları veri kaynaklarının detaylarını bilmeden bu interface aracılığıyla
 * verilere erişebilir.
 */
interface KitsuRepository {
    /**
     * Trend olan anime listesini getirir.
     * 
     * @return Trend olan anime listesi, hata durumunda boş liste döner
     */
    suspend fun getTrendingAnimeList(): List<AnimeData>
    
    /**
     * Belirtilen ID'ye sahip animeyi getirir.
     * 
     * @param id Getirilecek anime'nin ID'si
     * @return Anime verisi, hata durumunda veya bulunamazsa null döner
     */
    suspend fun getAnimeById(id:Int): AnimeData?
}