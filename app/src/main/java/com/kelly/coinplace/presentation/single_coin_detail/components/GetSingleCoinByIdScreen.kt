package com.kelly.coinplace.presentation.single_coin_detail.components

import android.util.Log
import android.util.Log.INFO
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kelly.coinplace.presentation.single_coin_detail.GetSingleCoinByIdViewModel
import com.kelly.coinplace.presentation.single_coin_detail.GetSingleCoinFromBothState

@Composable
fun GetSingleCoinByIdScreen(
    navController: NavController,
    viewModel: GetSingleCoinByIdViewModel = hiltViewModel()
) {
    val state = viewModel.getSingleCoinFromBothState.value
    SingleCoinByIdFinalScreen(
        modifier = Modifier.fillMaxSize(),
        coinSingle = state.data.first!!,
        coinSingleDesc = state.data.second!!
    ) {
        navController.navigateUp()
    }

    when {
        state.isLoading -> {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize()
            )
        }
        state.error.localizedMessage?.isNotEmpty() == true -> {
            Text(
                text = state.error.localizedMessage ?: "An Error Occurred",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}