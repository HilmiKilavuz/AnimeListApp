package com.kilavuzhilmi.anime_list_app.ui.screen.anime_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kilavuzhilmi.anime_list_app.domain.model.AnimeData
import com.kilavuzhilmi.anime_list_app.domain.repository.KitsuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * TrendingAnimeListViewModel - Trend olan anime listesi ekranı için ViewModel
 * 
 * ViewModel, UI ile veri katmanı arasında bir köprü görevi görür.
 * Bu ViewModel, trend olan animelerin listesini repository'den çeker ve UI'a sunar.
 * Jetpack ViewModel kullanarak, konfigürasyon değişiklikleri (ekran döndürme vb.)
 * sırasında verilerin korunmasını sağlar.
 * 
 * @property repository KitsuRepository, Hilt ile enjekte edilir ve anime verilerine erişim sağlar
 * 
 * @HiltViewModel Hilt kütüphanesinin bu sınıfı ViewModel olarak tanımasını ve
 * bağımlılıklarını otomatik olarak enjekte etmesini sağlar
 */
@HiltViewModel
class TrendingAnimeListViewModel  @Inject constructor(
    val repository: KitsuRepository
): ViewModel() {
    /**
     * Anime listesini tutan MutableStateFlow.
     * 
     * StateFlow, Kotlin Coroutines Flow API'sine dayalı bir observable state holder'dır.
     * Reactive programming modelini kullanarak UI'ın veri değişikliklerine tepki vermesini sağlar.
     * 
     * private değişken üzerinden içeride güncelleme yapılır, dışarıdan değiştirilemez.
     */
    private var _animeList = MutableStateFlow<List<AnimeData>>(emptyList())
    
    /**
     * Dışarıya sadece okunabilir bir StateFlow olarak sunulur.
     * UI bileşenleri bu property'yi gözlemleyerek veri değişikliklerine tepki verir.
     */
    val animeList= _animeList.asStateFlow()

    /**
     * ViewModel oluşturulduğunda verileri yükler.
     * init bloğu, sınıf oluşturulduğunda otomatik olarak çalışır.
     */
    init {
        // viewModelScope, ViewModel yaşam döngüsüne bağlı bir coroutine scope'u sağlar
        // ViewModel yok edildiğinde tüm coroutine'ler otomatik olarak iptal edilir
        viewModelScope.launch {
            // StateFlow'u günceller, repository'den gelen veriyi UI'a aktarır
            _animeList.update { repository.getTrendingAnimeList() }
        }
    }

}