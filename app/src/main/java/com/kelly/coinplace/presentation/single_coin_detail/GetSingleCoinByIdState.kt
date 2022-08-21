package com.kelly.coinplace.presentation.single_coin_detail

import com.kelly.coinplace.domain.model.CoinSingle

data class GetSingleCoinByIdState(
    val isLoading: Boolean = false,
    val data: CoinSingle? = null,
    val error: Throwable = Throwable()
)
