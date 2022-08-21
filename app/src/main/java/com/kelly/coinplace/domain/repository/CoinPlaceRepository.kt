package com.kelly.coinplace.domain.repository

import com.kelly.coinplace.data.remote.dto.CoinSingleDescDto
import com.kelly.coinplace.data.remote.dto.CoinSingleDto
import com.kelly.coinplace.data.remote.dto.CoinsDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CoinPlaceRepository {

    suspend fun getAllCoins(): Response<List<CoinsDto>>

    suspend fun getSingleCoinById(coinId: String): Response<CoinSingleDto>

    suspend fun getSingleCoinDescById(coinId: String): Response<CoinSingleDescDto>
}