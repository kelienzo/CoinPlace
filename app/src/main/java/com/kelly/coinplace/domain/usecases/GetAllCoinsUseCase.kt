package com.kelly.coinplace.domain.usecases

import com.google.gson.Gson
import com.kelly.coinplace.common.Constants
import com.kelly.coinplace.common.ResultHandler
import com.kelly.coinplace.data.remote.dto.toCoins
import com.kelly.coinplace.domain.model.Coins
import com.kelly.coinplace.domain.repository.CoinPlaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCoinsUseCase @Inject constructor(
    private val coinPlaceRepository: CoinPlaceRepository
) {

    fun getAllCoins(): Flow<ResultHandler<List<Coins>?>> = flow {
        try {
            emit(ResultHandler.Loading)

            val response = coinPlaceRepository.getAllCoins()
            val result = response.body()?.map { it.toCoins() }
            if (response.isSuccessful && result != null) {
                emit(ResultHandler.Success(data = result))
            } else {
                Constants.ERROR_MESSAGE =
                    Gson().fromJson(response.errorBody()?.charStream(), Coins::class.java).error ?: ""
                emit(ResultHandler.Error(errorData = result))
            }
        } catch (t: Throwable) {
            emit(ResultHandler.Exception(throwable = Constants.errorHandler(throwable = t)))
        }
    }
}