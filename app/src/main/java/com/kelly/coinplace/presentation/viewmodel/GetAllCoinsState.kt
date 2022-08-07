package com.kelly.coinplace.presentation.viewmodel

import com.kelly.coinplace.domain.model.Coins

data class GetAllCoinsState(
    val isLoading: Boolean = false,
    val data: List<Coins> = emptyList(),
    val error: Throwable = Throwable("")
) {
}