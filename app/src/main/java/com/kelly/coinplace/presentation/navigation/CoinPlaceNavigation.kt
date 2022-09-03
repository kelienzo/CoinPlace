package com.kelly.coinplace.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kelly.coinplace.common.Constants
import com.kelly.coinplace.presentation.all_coins.components.GetAllCoins
import com.kelly.coinplace.presentation.screens.CoinPlaceScreens
import com.kelly.coinplace.presentation.single_coin_detail.components.GetSingleCoinByIdScreen

@Composable
fun CoinPlaceNavigationScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CoinPlaceScreens.CoinListScreen.route
    ) {
        composable(
            route = CoinPlaceScreens.CoinListScreen.route
        ) {
            GetAllCoins(navController = navController)
        }
        composable(
            route = CoinPlaceScreens.SingleCoinDetailScreen.route + "/{${Constants.coinId}}"
        ) {
            GetSingleCoinByIdScreen(navController = navController)
        }
    }
}