package com.kelly.coinplace.data.remote

import com.kelly.coinplace.data.remote.dto.CoinSingleDto
import com.kelly.coinplace.data.remote.dto.CoinsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPlaceApi {

    @GET("tickers")
    suspend fun getAllCoins(): Response<List<CoinsDto>>

    @GET("tickers/{coin_id}")
    suspend fun getSingleCoinById(@Path("coin_id") coinId: String): Response<CoinSingleDto>
}