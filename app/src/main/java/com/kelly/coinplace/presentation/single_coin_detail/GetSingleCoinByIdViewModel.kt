package com.kelly.coinplace.presentation.single_coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelly.coinplace.common.Constants
import com.kelly.coinplace.common.ResultHandler
import com.kelly.coinplace.domain.usecases.CoinPlaceUseCases
import com.kelly.coinplace.presentation.single_coin_detail.components.GetCoinFromBothState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetSingleCoinByIdViewModel @Inject constructor(
    private val coinPlaceUseCases: CoinPlaceUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private val _getSingleCoinByIdState = MutableStateFlow(GetSingleCoinByIdState())
//    private val getSingleCoinByIdState = _getSingleCoinByIdState.asStateFlow()

    private val _getSingleCoinByIdState = mutableStateOf(GetSingleCoinByIdState())
    val getSingleCoinByIdState: State<GetSingleCoinByIdState> = _getSingleCoinByIdState

//    private val _getSingleCoinDescByIdState = MutableStateFlow(GetSingleCoinDescByIdState())
//    private val getSingleCoinDescByIdState = _getSingleCoinDescByIdState.asStateFlow()

//    private val _getSingleCoinFromBothState = mutableStateOf(GetCoinFromBothState())
//    val getSingleCoinFromBothState: State<GetCoinFromBothState> = _getSingleCoinFromBothState

    init {
        savedStateHandle.get<String>(Constants.coinId)?.let {
            getSingleCoinById(coinId = it)
//            getSingleCoinDescById(coinId = it)
        }

//        getSingleCoinFromBothState()
    }

//    private fun getSingleCoinFromBothState() {
//        _getSingleCoinByIdState.combine(_getSingleCoinDescByIdState) { coin, coinDesc ->
//            when {
//                coinDesc.isLoading -> {
//                    _getSingleCoinFromBothState.value = GetCoinFromBothState(isLoading = true)
//                }
//                coinDesc.error.localizedMessage?.isNotEmpty() == true -> {
//                    _getSingleCoinFromBothState.value = GetCoinFromBothState(error = coinDesc.error)
//                }
//                coinDesc.data != null && coin.data != null -> {
//                    _getSingleCoinFromBothState.value =
//                        GetCoinFromBothState(coinSingle = coin.data, coinSingleDesc = coinDesc.data)
//                }
//            }
//        }
//    }


    private fun getSingleCoinById(coinId: String) {
        viewModelScope.launch {
            coinPlaceUseCases.getSingleCoinByIdUseCase.getSingleCoinById(
                coinId = coinId
            ).collect {
                when (it) {
                    is ResultHandler.Error -> {
                        _getSingleCoinByIdState.value =
                            GetSingleCoinByIdState(error = Throwable(it.errorData.error))
                    }
                    is ResultHandler.Exception -> {
                        _getSingleCoinByIdState.value = GetSingleCoinByIdState(
                            error = it.throwable
                        )
                    }
                    ResultHandler.Loading -> {
                        _getSingleCoinByIdState.value = GetSingleCoinByIdState(isLoading = true)
                    }
                    is ResultHandler.Success -> {
                        _getSingleCoinByIdState.value = GetSingleCoinByIdState(
                            data = it.data
                        )
                    }
                }
            }
        }
    }

//    private fun getSingleCoinDescById(coinId: String) {
//        viewModelScope.launch {
//            coinPlaceUseCases.getSingleCoinByIdUseCase.getSingleCoinDescById(
//                coinId = coinId
//            ).collect {
//                when (it) {
//                    is ResultHandler.Error -> {
//                        _getSingleCoinDescByIdState.value =
//                            GetSingleCoinDescByIdState(error = Throwable(it.errorData.error))
//                    }
//                    is ResultHandler.Exception -> {
//                        _getSingleCoinDescByIdState.value = GetSingleCoinDescByIdState(
//                            error = it.throwable
//                        )
//                    }
//                    ResultHandler.Loading -> {
//                        _getSingleCoinDescByIdState.value = GetSingleCoinDescByIdState(
//                            isLoading = true
//                        )
//                    }
//                    is ResultHandler.Success -> {
//                        _getSingleCoinDescByIdState.value = GetSingleCoinDescByIdState(
//                            data = it.data
//                        )
//                    }
//                }
//            }
//        }
//    }
}