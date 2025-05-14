@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.kilavuzhilmi.anime_list_app.ui.screen.anime_list

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kilavuzhilmi.anime_list_app.ui.screen.anime_list.composable.AnimeCard

/**
 * Trend olan animelerin listelendiği ekran
 * 
 * Bu Composable, API'den çekilen trend animeleri listeler.
 * Jetpack Compose'un SharedTransition API'sini kullanarak, anime seçildiğinde
 * detay ekranına animasyonlu geçiş sağlar.
 * 
 * Yükleme durumunda yükleme göstergesi (CircularProgressIndicator) gösterilir,
 * veriler yüklendiğinde anime listesi görüntülenir.
 * 
 * @param onAnimeClick Anime seçildiğinde çağrılacak fonksiyon, kapak görseli ve ID parametreleriyle
 * @param animatedVisibilityScope Animasyonlu geçişler için gereken scope
 * @param viewModel TrendingAnimeListViewModel, Hilt ile otomatik enjekte edilir
 */
@Composable
fun SharedTransitionScope.TrendingAnimeListScreen(
    onAnimeClick: (String?, Int?) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: TrendingAnimeListViewModel = hiltViewModel(),

    ){
    // ViewModel'den anime listesini alır ve state değişikliklerini izler
    val animeList by viewModel.animeList.collectAsStateWithLifecycle()
    
    // Scaffold, Material Design temel ekran yapısını sağlar
    Scaffold() {innerPadding->
        // AnimatedContent, farklı içerikler arasında animasyonlu geçiş sağlar
        // Burada yükleme durumu ve liste arasında geçiş yapılır
        AnimatedContent(targetState = animeList.isEmpty(), label = "") { isEmpty ->
            if(isEmpty){
                // Yükleme durumunda, ekranın ortasında bir yükleme göstergesi gösterilir
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){

                    CircularProgressIndicator()
                }


            }else{
                // Veriler yüklendiğinde anime listesi gösterilir
                LazyColumn (
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        end = 20.dp,
                        top = 15.dp + innerPadding.calculateTopPadding(),
                        bottom = 15.dp + innerPadding.calculateBottomPadding()


                    )

                ){
                    // Liste başlığı
                    item {
                        Text(text = "Trending Anime",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold
                        )

                    }
                    
                    // Anime kartlarının listelenmesi
                    items(animeList) {anime->
                        // Her anime için bir kart oluşturulur
                        // Karta tıklandığında onAnimeClick ile navigasyon sağlanır
                        AnimeCard(anime = anime, onClick = {
                            onAnimeClick(anime.attributes?.posterImage?.original.toString(),anime.id.toInt())

                        },
                            animatedVisibilityScope = animatedVisibilityScope
                        )


                    }
                }

            }

        }

    }

}


