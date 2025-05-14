@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.kilavuzhilmi.anime_list_app.ui.screen.anime

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.size.Scale

/**
 * Anime detay ekranı
 * 
 * Bu Composable, seçilen bir anime'nin detay bilgilerini gösterir.
 * Jetpack Compose'un SharedTransition API'sini kullanarak, liste ekranından detay ekranına
 * geçişte anime kapak görselini animasyonlu şekilde geçirir.
 * 
 * @param id Anime ID'si, API'den detay bilgileri almak için kullanılır
 * @param coverImage Anime kapak görseli URL'si, API'den gelmeden önce gösterilir
 * @param animatedVisibilityScope Animasyonlu geçişler için gereken scope
 * @param viewModel AnimeViewModel, Hilt ile otomatik enjekte edilir
 */
@Composable
fun SharedTransitionScope.AnimeScreen(
    id: String,
    coverImage: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: AnimeViewModel = hiltViewModel()
) {
    /**
     * LaunchedEffect, Composable'ın ilk oluşturulduğunda bir kez çalışan yan etki bloğudur.
     * Burada anime ID'si kullanılarak API'den anime detayları çekilir.
     */
    LaunchedEffect(key1 = true) {
        viewModel.getAnimeById(id.toInt())

    }
    // ViewModel'den anime verilerini alır ve state değişikliklerini izler
    val anime by viewModel.anime.collectAsStateWithLifecycle()
    
    // Scaffold, Material Design temel ekran yapısını sağlar (appbar, content, bottombar alanları)
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding() + 10.dp)
        ) {
            // Kapak görseli
            item {
                AsyncImage(
                    model = coverImage, contentDescription = "Cover Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(
                            RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                        ).sharedElement(
                            // SharedTransition API ile liste ekranından kapak görselinin
                            // animasyonlu geçişini sağlar
                            rememberSharedContentState(key = id),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    contentScale = ContentScale.Crop
                )
            }
            
            // Anime detayları (başlık, yıl, puan, özet)
            item {
                if (anime != null) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 16.dp)
                            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Anime başlığı
                        Text(
                            text = anime?.attributes?.canonicalTitle.toString(),
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        
                        // Yapım yılı ve puan bilgileri
                        Row() {
                            // Yapım yılı (ilk tarih ögesi alınır)
                            Text(
                                text = anime?.attributes?.startDate?.split("-")?.first().toString(),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "-", modifier = Modifier.padding(horizontal = 4.dp)
                            )
                            
                            // Yıldız ikonu ve puan
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(1.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Star,
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 4.dp)
                                )
                                Text(
                                    text = anime?.attributes?.averageRating.toString(),
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Medium
                                )

                            }

                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Anime özeti bölümü
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(
                                text = "Synopsis",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = anime?.attributes?.synopsis.toString(),

                                )

                        }
                    }
                }

            }

        }

    }


}