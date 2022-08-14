package com.kelly.coinplace.domain.usecases

data class CoinPlaceUseCases(
    val getAllCoinsUseCase: GetAllCoinsUseCase,
    val getSingleCoinByIdUseCase: GetSingleCoinByIdUseCase
)
