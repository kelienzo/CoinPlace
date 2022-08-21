package com.kelly.coinplace.data.remote

import com.kelly.coinplace.data.remote.dto.CoinSingleDescDto
import com.kelly.coinplace.data.remote.dto.CoinSingleDto
import com.kelly.coinplace.data.remote.dto.CoinsDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPlaceApi {

    @GET("tickers")
    suspend fun getAllCoins(): Response<List<CoinsDto>>

    @GET("tickers/{coin_id}")
    suspend fun getSingleCoinById(@Path("coin_id") coinId: String): Response<CoinSingleDto>

    @GET("coins/{coin_id}")
    suspend fun getSingleCoinDescById(@Path("coin_id") coinId: String): Response<CoinSingleDescDto>
}