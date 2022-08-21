package com.kelly.coinplace.presentation.single_coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelly.coinplace.common.ResultHandler
import com.kelly.coinplace.domain.usecases.CoinPlaceUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetSingleCoinByIdViewModel @Inject constructor(
    private val coinPlaceUseCases: CoinPlaceUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _getSingleCoinByIdState = MutableStateFlow(GetSingleCoinByIdState())
    private val getSingleCoinByIdState: StateFlow<GetSingleCoinByIdState> = _getSingleCoinByIdState

    private val _getSingleCoinDescByIdState = MutableStateFlow(GetSingleCoinDescByIdState())
    private val getSingleCoinDescByIdState: StateFlow<GetSingleCoinDescByIdState>
        get() = _getSingleCoinDescByIdState

    private val _getSingleCoinFromBothState =
        mutableStateOf<GetSingleCoinFromBothState>(GetSingleCoinFromBothState.EmptyState)
    val getSingleCoinFromBothState: State<GetSingleCoinFromBothState>
        get() = _getSingleCoinFromBothState

    init {
        savedStateHandle.get<String>("coinId")?.let {
            getSingleCoinById(coinId = it)
            getSingleCoinDescById(coinId = it)
        }

        getSingleCoinFromBothState()
    }

    private fun getSingleCoinFromBothState() {
        getSingleCoinByIdState.zip(getSingleCoinDescByIdState) { stateOne, stateTwo ->
            if (stateOne.isLoading && stateTwo.isLoading) {
                _getSingleCoinFromBothState.value = GetSingleCoinFromBothState.Loading
            } else if (stateOne.error.localizedMessage?.isNotEmpty() == true && stateTwo.error.localizedMessage?.isNotEmpty() == true) {
                _getSingleCoinFromBothState.value = GetSingleCoinFromBothState.Error(
                    error = stateOne.error
                )
            } else {
                _getSingleCoinFromBothState.value = GetSingleCoinFromBothState.DataGotten(
                    data = Pair(
                        first = stateOne.data,
                        second = stateTwo.data
                    )
                )
            }
        }
    }


    private fun getSingleCoinById(coinId: String) {
        viewModelScope.launch {
            coinPlaceUseCases.getSingleCoinByIdUseCase.getSingleCoinById(coinId = coinId)
                .collect { result ->
                    when (result) {
                        is ResultHandler.Error -> {
                            _getSingleCoinByIdState.value =
                                GetSingleCoinByIdState(error = Throwable(result.errorData.error))
                        }
                        is ResultHandler.Exception -> {
                            _getSingleCoinByIdState.value = GetSingleCoinByIdState(
                                error = result.throwable
                            )
                        }
                        ResultHandler.Loading -> {
                            _getSingleCoinByIdState.value = GetSingleCoinByIdState(isLoading = true)
                        }
                        is ResultHandler.Success -> {
                            _getSingleCoinByIdState.value = GetSingleCoinByIdState(
                                data = result.data
                            )
                        }
                    }
                }
        }
    }

    private fun getSingleCoinDescById(coinId: String) {
        viewModelScope.launch {
            coinPlaceUseCases.getSingleCoinByIdUseCase.getSingleCoinDescById(coinId = coinId)
                .collect { result ->
                    when (result) {
                        is ResultHandler.Error -> {
                            _getSingleCoinDescByIdState.value =
                                GetSingleCoinDescByIdState(error = Throwable(result.errorData.error))
                        }
                        is ResultHandler.Exception -> {
                            _getSingleCoinDescByIdState.value = GetSingleCoinDescByIdState(
                                error = result.throwable
                            )
                        }
                        ResultHandler.Loading -> {
                            _getSingleCoinDescByIdState.value = GetSingleCoinDescByIdState(
                                isLoading = true
                            )
                        }
                        is ResultHandler.Success -> {
                            _getSingleCoinDescByIdState.value = GetSingleCoinDescByIdState(
                                data = result.data
                            )
                        }
                    }
                }
        }
    }
}