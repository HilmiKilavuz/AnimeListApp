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

@OptIn(ExperimentalSharedTransitionApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )
        setContent {
            Anime_List_AppTheme {
                val navController = rememberNavController()

                SharedTransitionLayout {
                    NavHost(navController = navController, startDestination = TrendingAnimeRoute) {
                        composable<TrendingAnimeRoute> {
                            TrendingAnimeListScreen(
                                onAnimeClick = { cover, id ->
                                    navController.navigate(AnimeRoute(id = id.toString(), coverImage = cover.toString()))
                                },
                                animatedVisibilityScope = this
                            )
                        }
                        composable<AnimeRoute> {
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

@Serializable
data object TrendingAnimeRoute

@Serializable
data class AnimeRoute(val id: String, val coverImage: String?)
