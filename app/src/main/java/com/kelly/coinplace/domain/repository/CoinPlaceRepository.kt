package com.kelly.coinplace.domain.repository

import com.kelly.coinplace.data.remote.dto.CoinsDto
import retrofit2.Response

interface CoinPlaceRepository {

    suspend fun getAllCoins(): Response<List<CoinsDto>>
}