package com.jikanAPI

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.jikanAPI.presentation.detail.AnimeDetailScreen
import com.jikanAPI.presentation.list.AnimeListScreen
import com.jikanAPI.ui.theme.JikanTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JikanTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "anime_list_screen"
                ) {
                    composable("anime_list_screen") {
                        AnimeListScreen(navController = navController)
                    }
                    composable("anime_detail_screen/{animeId}") {
                        AnimeDetailScreen()
                    }
                }
            }
        }
    }
}