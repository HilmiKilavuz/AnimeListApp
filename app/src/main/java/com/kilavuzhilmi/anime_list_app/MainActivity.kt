package com.kilavuzhilmi.anime_list_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kilavuzhilmi.anime_list_app.ui.screen.anime.AnimeScreen
import com.kilavuzhilmi.anime_list_app.ui.screen.anime_list.TrendingAnimeListScreen
import com.kilavuzhilmi.anime_list_app.ui.theme.Anime_List_AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

/**
 * MainActivity - Uygulamanın ana aktivitesi
 * 
 * Bu sınıf, uygulamanın başlangıç noktasıdır ve Jetpack Compose ile kullanıcı arayüzünü ayarlar.
 * Compose Navigation ve SharedTransition API'lerini kullanarak ekranlar arası geçişi yönetir.
 * 
 * @OptIn(ExperimentalSharedTransitionApi::class) - SharedTransition API'si henüz deneysel olduğu için
 * bu anotasyon ile açıkça kabul edilmesi gerekir.
 * 
 * @AndroidEntryPoint - Hilt bağımlılık enjeksiyonu için bu aktivitenin bir giriş noktası olduğunu belirtir.
 * Bu sayede, Hilt ViewModel'ler ve diğer bağımlılıklar bu aktiviteye enjekte edilebilir.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Edge-to-edge görünüm sağlar (durum çubuğu ve gezinti çubuğu dahil tam ekran)
        // Şeffaf durum çubuğu ayarlanır
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )
        
        // Compose UI'ın ayarlanması
        setContent {
            // Uygulama teması uygulanır
            Anime_List_AppTheme {
                // Navigation Controller oluşturulur ve hatırlanır
                val navController = rememberNavController()

                // SharedTransitionLayout, ekranlar arası geçişlerde paylaşılan öğelerin
                // animasyonlu geçişini sağlar
                SharedTransitionLayout {
                    // Navigation Host, uygulamanın ekranları arasında gezinmeyi kontrol eder
                    NavHost(navController = navController, startDestination = TrendingAnimeRoute) {
                        // Trend olan animeler listesi ekranı
                        composable<TrendingAnimeRoute> {
                            TrendingAnimeListScreen(
                                // Bir anime'ye tıklandığında, anime detay ekranına navigasyon yapılır
                                onAnimeClick = { cover, id ->
                                    navController.navigate(AnimeRoute(id = id.toString(), coverImage = cover.toString()))
                                },
                                animatedVisibilityScope = this
                            )
                        }
                        
                        // Anime detay ekranı
                        composable<AnimeRoute> {
                            // Route parametreleri AnimeRoute sınıfına dönüştürülür
                            val args = it.toRoute<AnimeRoute>()
                            AnimeScreen(id = args.id, coverImage = args.coverImage.toString(),
                                animatedVisibilityScope = this)


                        }

                    }

                }


            }
        }
    }
}

/**
 * Trend olan animeler listesi ekranının rota tanımı
 * 
 * @Serializable - Kotlin Serialization kütüphanesi ile serileştirilebilir olduğunu belirtir
 * data object, parametre almayan bir rota için kullanılır
 */
@Serializable
data object TrendingAnimeRoute

/**
 * Anime detay ekranının rota tanımı
 * 
 * @property id Anime ID'si, ekrana geçiş yaparken zorunlu parametre
 * @property coverImage Anime kapak görseli URL'si, null olabilir
 * 
 * @Serializable - Kotlin Serialization kütüphanesi ile serileştirilebilir olduğunu belirtir
 */
@Serializable
data class AnimeRoute(val id: String, val coverImage: String?)
