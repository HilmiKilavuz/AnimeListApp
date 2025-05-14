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

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Provides
    @Singleton
    fun providesMoshi(): Moshi=
        Moshi.Builder()
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()
    @Provides
    @Singleton
    fun providesKitsuApi(moshi: Moshi): KitsuApi=
        Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .baseUrl(KitsuApi.baseUrl).build().create(KitsuApi::class.java)

    @Provides
    @Singleton
    fun providesKitsuRepository(api: KitsuApi): KitsuRepository=
        KitsuRepositoryImpl(api)


}