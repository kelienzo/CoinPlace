package com.kelly.coinplace.data.remote

import com.kelly.coinplace.data.remote.dto.CoinsDto
import retrofit2.Response
import retrofit2.http.GET

interface CoinPlaceApi {

    @GET("tickers")
    suspend fun getAllCoins(): Response<List<CoinsDto>>
}