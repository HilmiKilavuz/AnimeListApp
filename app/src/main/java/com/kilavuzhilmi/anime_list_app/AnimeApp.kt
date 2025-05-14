package com.kilavuzhilmi.anime_list_app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * AnimeApp - Uygulama sınıfı
 * 
 * Bu sınıf, uygulamanın temel Application sınıfını genişletir ve uygulamanın yaşam döngüsünü yönetir.
 * 
 * @HiltAndroidApp anotasyonu, Hilt bağımlılık enjeksiyon kütüphanesinin başlangıç noktasıdır.
 * Bu anotasyon, Hilt için gerekli olan altyapı kodlarını otomatik olarak oluşturur ve
 * uygulamaya Dependency Injection (Bağımlılık Enjeksiyonu) yeteneği kazandırır.
 * 
 * Hilt, Dagger tabanlı bir bağımlılık enjeksiyon kütüphanesidir ve Android uygulamalarında
 * bağımlılıkların yönetimini kolaylaştırır. Bu anotasyon sayesinde, ViewModel, Repository gibi
 * bileşenlere bağımlılıkların otomatik olarak enjekte edilmesi sağlanır.
 */
@HiltAndroidApp
class AnimeApp : Application()
