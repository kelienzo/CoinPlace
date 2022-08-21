package com.kelly.coinplace.presentation.single_coin_detail.components

import com.kelly.coinplace.domain.model.CoinSingle
import com.kelly.coinplace.domain.model.CoinSingleDesc

data class GetCoinFromBothState(
    val isLoading: Boolean = false,
    val data: Pair<CoinSingle?, CoinSingleDesc?> = Pair(null, null),
    val error: Throwable = Throwable("")
)
