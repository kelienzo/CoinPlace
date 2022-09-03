package com.kelly.coinplace.presentation.single_coin_detail.components

import com.kelly.coinplace.domain.model.CoinSingle
import com.kelly.coinplace.domain.model.CoinSingleDesc

data class GetCoinFromBothState(
    val isLoading: Boolean = false,
    val coinSingle: CoinSingle? = null,
    val coinSingleDesc: CoinSingleDesc? = null,
    val error: Throwable = Throwable("")
)
