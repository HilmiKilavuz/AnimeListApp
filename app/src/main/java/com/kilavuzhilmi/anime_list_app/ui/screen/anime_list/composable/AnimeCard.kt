@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.kilavuzhilmi.anime_list_app.ui.screen.anime_list.composable

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Start
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kilavuzhilmi.anime_list_app.domain.model.AnimeData

/**
 * Anime kartı bileşeni
 * 
 * Bu Composable, anime liste ekranında her bir anime'yi gösteren kart bileşenidir.
 * Kartın içinde anime posteri, puanı, başlığı ve kısa açıklaması yer alır.
 * SharedTransition API'si kullanılarak, poster görseli için liste ekranından
 * detay ekranına animasyonlu geçiş sağlanır.
 * 
 * @param anime Görüntülenecek anime verisi
 * @param onClick Karta tıklandığında çağrılacak fonksiyon
 * @param animatedVisibilityScope Animasyonlu geçişler için gereken scope
 * @param modifier Composable'ı özelleştirmek için kullanılabilecek modifier (isteğe bağlı)
 */
@Composable
fun SharedTransitionScope.AnimeCard(
    anime: AnimeData,
    onClick: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier= Modifier

) {
    // Material3 Card bileşeni, tıklanabilir kart sağlar
    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        // Yatay düzende poster ve bilgiler
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(6.dp)
        ){
            // Anime poster görseli
            AsyncImage(model = anime.attributes.posterImage.original,
                contentDescription = "Poster" ,
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(10.dp)).sharedElement(
                        // SharedTransition API ile liste ekranından detay ekranına
                        // poster görselinin animasyonlu geçişini sağlar
                        rememberSharedContentState(key = anime.id?: ""),
                        animatedVisibilityScope= animatedVisibilityScope
                    ),
                contentScale = ContentScale.Crop
            )
            
            // Anime bilgileri sütunu
            Column {
                // Puan bilgisi (yıldız ikonu ile birlikte)
                Row(modifier= Modifier.background(
                    color = Color(0xFFC4C7EB),
                    shape = RoundedCornerShape(30.dp)).
                    padding(horizontal = 6.dp, vertical = 2.dp)

                ){
                    Icon(imageVector = Icons.Rounded.Star,
                        contentDescription = "",
                        tint = Color.Yellow)
                    Text(text = anime.attributes?.averageRating.toString())
                }
                
                // Anime başlığı
                Text(text = anime.attributes?.canonicalTitle.toString(),
                    style = MaterialTheme.typography.titleMedium)
                
                // Kısa özet metni (max 2 satır)
                Text(text = anime.attributes?.synopsis.toString(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2)

            }

        }

    }

}


