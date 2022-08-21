package com.kelly.coinplace.presentation.single_coin_detail

import com.kelly.coinplace.domain.model.CoinSingleDesc

data class GetSingleCoinDescByIdState(
    val isLoading: Boolean = false,
    val data: CoinSingleDesc? = null,
    val error: Throwable = Throwable()
)
