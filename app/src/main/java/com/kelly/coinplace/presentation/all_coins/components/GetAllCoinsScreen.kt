package com.kelly.coinplace.presentation.all_coins.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kelly.coinplace.presentation.all_coins.GetAllCoinsViewModel

@Composable
fun GetAllCoins(viewModel: GetAllCoinsViewModel = hiltViewModel()) {
    val getAllCoinsState = viewModel.getAllCoinsState.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(getAllCoinsState.data) { coins ->
                AllCoinsItem(
                    coins = coins,
                    modifier = Modifier
                        .clickable {
                            //TODO
                        }
                        .border(
                            BorderStroke(
                                width = 1.dp,
                                color = Color.Transparent
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(2.dp)
                        .fillMaxWidth()
                )
            }
        }

        when {
            getAllCoinsState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            getAllCoinsState.error.localizedMessage?.isNotEmpty() == true -> {
                Text(
                    text = getAllCoinsState.error.localizedMessage ?: "An Error Occurred",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}