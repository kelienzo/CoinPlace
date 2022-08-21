package com.kelly.coinplace.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kelly.coinplace.presentation.all_coins.components.GetAllCoins
import com.kelly.coinplace.presentation.navigation.CoinPlaceNavigationScreen
import com.kelly.coinplace.ui.theme.CoinPlaceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinPlaceTheme {
                CoinPlaceNavigationScreen()
            }
        }
    }
}