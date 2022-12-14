package com.kelly.coinplace.domain.usecases

import com.google.gson.Gson
import com.kelly.coinplace.common.Constants
import com.kelly.coinplace.common.ResultHandler
import com.kelly.coinplace.data.remote.dto.toCoinSingle
import com.kelly.coinplace.data.remote.dto.toCoinSingleDesc
import com.kelly.coinplace.domain.model.CoinSingle
import com.kelly.coinplace.domain.model.CoinSingleDesc
import com.kelly.coinplace.domain.repository.CoinPlaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSingleCoinByIdUseCase @Inject constructor(
    private val coinPlaceRepository: CoinPlaceRepository
) {

    suspend fun getSingleCoinById(coinId: String): Flow<ResultHandler<CoinSingle>> = flow {
        try {
            emit(ResultHandler.Loading)

            val response = coinPlaceRepository.getSingleCoinById(coinId = coinId)
            val result = response.body()?.toCoinSingle()
            if (response.isSuccessful && result != null) {
                emit(ResultHandler.Success(data = result))
            } else {
                val error =
                    Gson().fromJson(response.errorBody()?.charStream(), CoinSingle::class.java)
                emit(ResultHandler.Error(errorData = error))
            }
        } catch (t: Throwable) {
            emit(ResultHandler.Exception(throwable = Constants.errorHandler(throwable = t)))
        }
    }

    suspend fun getSingleCoinDescById(coinId: String): Flow<ResultHandler<CoinSingleDesc>> = flow {
        try {
            emit(ResultHandler.Loading)

            val response = coinPlaceRepository.getSingleCoinDescById(coinId = Constants.coinId)
            val result = response.body()?.toCoinSingleDesc()
            if (response.isSuccessful && result != null) {
                emit(ResultHandler.Success(data = result))
            } else {
                val error = Gson().fromJson(
                    response.errorBody()?.charStream(),
                    CoinSingleDesc::class.java
                )
                emit(ResultHandler.Error(errorData = error))
            }
        } catch (t: Throwable) {
            emit(ResultHandler.Exception(throwable = Constants.errorHandler(throwable = t)))
        }
    }
}