package com.kelly.coinplace.presentation.single_coin_detail

import com.kelly.coinplace.domain.model.CoinSingle
import com.kelly.coinplace.domain.model.CoinSingleDesc

sealed class GetSingleCoinFromBothState {
    object EmptyState: GetSingleCoinFromBothState()
    object Loading: GetSingleCoinFromBothState()
    data class DataGotten(val data: Pair<CoinSingle?, CoinSingleDesc?>): GetSingleCoinFromBothState()
    data class Error(val error: Throwable): GetSingleCoinFromBothState()
}
