package com.kilavuzhilmi.anime_list_app.data.network

import com.kilavuzhilmi.anime_list_app.data.network.dto.AnimeResponseDto
import com.kilavuzhilmi.anime_list_app.data.network.dto.TrendingAnimeListDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * KitsuApi - Kitsu anime veritabanı API'si için Retrofit interface'i
 * 
 * Bu interface, Kitsu.io REST API'si ile iletişim kurmak için kullanılır.
 * Retrofit kütüphanesi, bu interface'i otomatik olarak uygulayarak API çağrılarını gerçekleştirir.
 * 
 * Sandwich kütüphanesi (ApiResponse) kullanılarak, API cevapları daha iyi bir şekilde işlenebilir,
 * başarılı ve başarısız cevaplar için tip güvenli bir yapı sağlanır.
 */
interface KitsuApi {
    /**
     * Trend olan anime listesini getirir.
     * 
     * @GET anotasyonu ile HTTP GET isteği tanımlanır, "trending/anime" endpoint'ine istek yapılır.
     * suspend fonksiyonu, Kotlin Coroutine'leri ile asenkron çalışmayı sağlar.
     * 
     * @return ApiResponse tipinde TrendingAnimeListDto nesnesi döndürür. ApiResponse başarılı veya
     *         başarısız yanıtları işlemek için kullanılır.
     */
    @GET("trending/anime")
    suspend fun getTrendingAnimeList(): ApiResponse<TrendingAnimeListDto>

    /**
     * Belirtilen ID'ye sahip anime bilgilerini getirir.
     * 
     * @GET anotasyonu ile HTTP GET isteği tanımlanır, "anime/{id}" endpoint'ine istek yapılır.
     * @Path anotasyonu ile URL'deki {id} değişkeni dinamik olarak belirlenir.
     * 
     * @param id Detayları getirilecek anime'nin ID'si
     * @return ApiResponse tipinde AnimeResponseDto nesnesi döndürür.
     */
    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): ApiResponse<AnimeResponseDto>

    /**
     * API için sabit değerleri içeren companion object.
     * 
     * baseUrl: Kitsu.io API'sinin temel URL'i. Tüm API çağrıları bu URL'ye yapılır.
     */
    companion object{
        const val baseUrl = "https://kitsu.io/api/edge/"
    }

}