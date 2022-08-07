package com.kelly.coinplace.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelly.coinplace.common.ResultHandler
import com.kelly.coinplace.domain.usecases.CoinPlaceUseCases
import com.kelly.coinplace.domain.usecases.GetAllCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GetAllCoinsViewModel @Inject constructor(
    private val coinPlaceUseCases: CoinPlaceUseCases
) : ViewModel() {

    private val _getAllCoinsState = mutableStateOf(GetAllCoinsState())
    val getAllCoinsState: State<GetAllCoinsState> = _getAllCoinsState

    init {
        getAllCoins()
    }

    private fun getAllCoins() {
        coinPlaceUseCases.getAllCoinsUseCase
            .getAllCoins().onEach { result ->
                when (result) {
                    is ResultHandler.Loading -> {
                        _getAllCoinsState.value = GetAllCoinsState(isLoading = true)
                    }
                    is ResultHandler.Error -> {
                        _getAllCoinsState.value =
                            GetAllCoinsState(error = Throwable(result.errorData?.get(0)?.error))
                    }
                    is ResultHandler.Exception -> {
                        _getAllCoinsState.value = GetAllCoinsState(error = result.throwable)
                    }
                    is ResultHandler.Success -> {
                        _getAllCoinsState.value = GetAllCoinsState(data = result.data!!)
                    }
                }
            }.launchIn(viewModelScope)
    }
}