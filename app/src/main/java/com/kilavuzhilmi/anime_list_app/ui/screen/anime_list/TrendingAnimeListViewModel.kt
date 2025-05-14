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

@HiltViewModel
class TrendingAnimeListViewModel  @Inject constructor(
    val repository: KitsuRepository
): ViewModel() {
    private var _animeList = MutableStateFlow<List<AnimeData>>(emptyList())
    val animeList= _animeList.asStateFlow()

    init {
        viewModelScope.launch {
            _animeList.update { repository.getTrendingAnimeList() }
        }
    }

}