package com.kelly.coinplace.presentation.screens

sealed class CoinPlaceScreens(val route: String){
    object CoinListScreen: CoinPlaceScreens("coin_list")
    object SingleCoinDetailScreen: CoinPlaceScreens("single_coin_detail")
}
