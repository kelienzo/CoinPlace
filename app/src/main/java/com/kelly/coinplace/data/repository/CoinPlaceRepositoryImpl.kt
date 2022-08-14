package com.kelly.coinplace.data.repository

import com.kelly.coinplace.data.remote.CoinPlaceApi
import com.kelly.coinplace.data.remote.dto.CoinSingleDto
import com.kelly.coinplace.data.remote.dto.CoinsDto
import com.kelly.coinplace.domain.repository.CoinPlaceRepository
import retrofit2.Response
import javax.inject.Inject

class CoinPlaceRepositoryImpl @Inject constructor(
    private val api: CoinPlaceApi
) : CoinPlaceRepository {
    override suspend fun getAllCoins(): Response<List<CoinsDto>> {
        return api.getAllCoins()
    }

    override suspend fun getSingleCoin(coinId: String): Response<CoinSingleDto> {
        return api.getSingleCoinById(coinId = coinId)
    }
}