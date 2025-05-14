package com.kilavuzhilmi.anime_list_app.data.di

import com.kilavuzhilmi.anime_list_app.data.network.KitsuApi
import com.kilavuzhilmi.anime_list_app.data.respository.KitsuRepositoryImpl
import com.kilavuzhilmi.anime_list_app.domain.repository.KitsuRepository
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * AppModule - Dagger Hilt modülü
 * 
 * Bu sınıf Dependency Injection (Bağımlılık Enjeksiyonu) işlemleri için kullanılır.
 * Dagger Hilt, Android uygulamalarında bağımlılık enjeksiyonunu kolaylaştıran bir kütüphanedir.
 * 
 * @Module: Bu sınıfın bir Dagger modülü olduğunu belirtir, bağımlılıkları sağlayacak metodları içerir.
 * @InstallIn(SingletonComponent::class): Bu modülün uygulama yaşam döngüsü boyunca (singleton olarak) 
 * kullanılacağını belirtir.
 */
@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    /**
     * Moshi JSON parser kütüphanesini sağlar.
     * 
     * Moshi, JSON verileri ile Kotlin/Java nesneleri arasında dönüşüm yapan bir kütüphanedir.
     * API'den gelen JSON yanıtlarını Kotlin nesnelerine dönüştürmek için kullanılır.
     * 
     * @Provides: Dagger'a bu metodun bağımlılık sağladığını belirtir.
     * @Singleton: Bu bağımlılığın uygulama yaşam döngüsü boyunca tek bir instance olarak kullanılacağını belirtir.
     */
    @Provides
    @Singleton
    fun providesMoshi(): Moshi=
        Moshi.Builder()
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()
    
    /**
     * Kitsu API için Retrofit servisini sağlar.
     * 
     * Retrofit, HTTP API'leri için type-safe bir HTTP client kütüphanesidir.
     * Kitsu API ile iletişim kurmak için kullanılır.
     * 
     * MoshiConverterFactory: API yanıtlarını JSON'dan Kotlin nesnelerine dönüştürür.
     * ApiResponseCallAdapterFactory: API yanıtlarını Sandwich kütüphanesinin ApiResponse türüne dönüştürür,
     * bu da hata yönetimini kolaylaştırır.
     * 
     * @param moshi JSON dönüşümleri için kullanılacak Moshi nesnesi
     */
    @Provides
    @Singleton
    fun providesKitsuApi(moshi: Moshi): KitsuApi=
        Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .baseUrl(KitsuApi.baseUrl).build().create(KitsuApi::class.java)

    /**
     * KitsuRepository için implementasyon sağlar.
     * 
     * Repository pattern, veri kaynaklarına erişim için bir soyutlama katmanı sağlar.
     * Bu metod, KitsuRepository interface'i için somut bir implementasyon olan KitsuRepositoryImpl'i döndürür.
     * 
     * @param api Repository'nin kullanacağı KitsuApi nesnesi
     */
    @Provides
    @Singleton
    fun providesKitsuRepository(api: KitsuApi): KitsuRepository=
        KitsuRepositoryImpl(api)


}