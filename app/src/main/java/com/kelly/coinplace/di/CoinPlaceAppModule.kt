package com.kelly.coinplace.di

import com.kelly.coinplace.common.Constants
import com.kelly.coinplace.data.remote.CoinPlaceApi
import com.kelly.coinplace.data.repository.CoinPlaceRepositoryImpl
import com.kelly.coinplace.domain.repository.CoinPlaceRepository
import com.kelly.coinplace.domain.usecases.CoinPlaceUseCases
import com.kelly.coinplace.domain.usecases.GetAllCoinsUseCase
import com.kelly.coinplace.domain.usecases.GetSingleCoinByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinPlaceAppModule {

    @Provides
    @Singleton
    fun provideRetrofitApi(): CoinPlaceApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(Constants.getOkhttpClient())
            .build()
            .create(CoinPlaceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinPlaceRepository(coinPlaceApi: CoinPlaceApi): CoinPlaceRepository {
        return CoinPlaceRepositoryImpl(api = coinPlaceApi)
    }

    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideCoinPlaceUseCases(
        coinPlaceRepository: CoinPlaceRepository,
        coroutineDispatcher: CoroutineDispatcher
    ): CoinPlaceUseCases {
        return CoinPlaceUseCases(
            GetAllCoinsUseCase(coinPlaceRepository = coinPlaceRepository),
            GetSingleCoinByIdUseCase(
                coinPlaceRepository = coinPlaceRepository,
                coroutineDispatcher = coroutineDispatcher
            )
        )
    }
}