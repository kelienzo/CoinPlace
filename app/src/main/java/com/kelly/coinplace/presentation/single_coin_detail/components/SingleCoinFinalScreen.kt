package com.kelly.coinplace.presentation.single_coin_detail.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kelly.coinplace.domain.model.CoinSingle
import com.kelly.coinplace.domain.model.CoinSingleDesc

@Composable
fun SingleCoinByIdFinalScreen(
    modifier: Modifier = Modifier,
    coinSingle: CoinSingle,
    coinSingleDesc: CoinSingleDesc,
    onBackArrowPressed: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = modifier,
        topBar = {
            SingleCoinTopBar(
                coinSingle = coinSingle,
                modifier = Modifier.fillMaxWidth()
            ) {
                onBackArrowPressed()
            }
        },
        scaffoldState = scaffoldState
    ) {
        SingleCoinContents(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            coinSingle = coinSingle,
            coinSingleDesc = coinSingleDesc
        )
    }
}