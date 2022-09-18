package com.kelly.coinplace.presentation.single_coin_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kelly.coinplace.presentation.single_coin_detail.GetSingleCoinByIdViewModel
import java.text.DecimalFormat

@Composable
fun GetSingleCoinByIdScreen(
    navController: NavController,
    viewModel: GetSingleCoinByIdViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val state = viewModel.getSingleCoinByIdState.value
        val scaffoldState = rememberScaffoldState()
//    SingleCoinByIdFinalScreen(
//        modifier = Modifier.fillMaxSize(),
//        coinSingle = state.coinSingle!!,
//        coinSingleDesc = state.coinSingleDesc!!
//    ) {
//        navController.navigateUp()
//    }
        state.data?.let { singleCoin ->
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState,
                topBar = {
                    SingleCoinTopBar(
                        coinSingle = singleCoin,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        navController.navigateUp()
                    }
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    var formattedAmount = ""
                    var formattedAthPrice = ""
                    try {
                        formattedAmount = DecimalFormat("#,###,###,###.###")
                            .format(singleCoin.price.toFloat())
                        formattedAthPrice = DecimalFormat("#,###,###,###.###")
                            .format(singleCoin.athPrice.toFloat())
                    } catch (n: NumberFormatException) {
                        n.printStackTrace()
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "\$$formattedAmount",
                            style = MaterialTheme.typography.h4,
                            fontFamily = FontFamily.Serif
                        )
                        Text(
                            text = "${singleCoin.rank}",
                            style = MaterialTheme.typography.h4,
                            fontFamily = FontFamily.Serif
                        )
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        shape = MaterialTheme.shapes.large.copy(CornerSize(10.dp)),
                        elevation = 5.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            CoinDetailsSection(
                                modifier = Modifier.fillMaxWidth(),
                                detailsName = "Circulating Supply",
                                detailsValue = "${singleCoin.circulatingSupply}"
                            )
                            Divider()
                            CoinDetailsSection(
                                modifier = Modifier.fillMaxWidth(),
                                detailsName = "Total Supply",
                                detailsValue = "${singleCoin.totalSupply}"
                            )
                            Divider()
                            CoinDetailsSection(
                                modifier = Modifier.fillMaxWidth(),
                                detailsName = "Max Supply",
                                detailsValue = "${singleCoin.maxSupply}"
                            )
                            Divider()
                            CoinDetailsSection(
                                modifier = Modifier.fillMaxWidth(),
                                detailsName = "Percentage Change1h",
                                detailsValue = "${singleCoin.percentageChange1h}%"
                            )
                        }
                    }
                    Text(
                        text = "ATH Price\n" +
                                "\$$formattedAthPrice",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        fontFamily = FontFamily.Serif,
                        style = MaterialTheme.typography.h5
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .border(
                                width = 1.dp,
                                shape = MaterialTheme.shapes.medium.copy(CornerSize(10.dp)),
                                color = MaterialTheme.colors.primary
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Market Capitalization\n" +
                                    "${singleCoin.marketCap}",
                            modifier = Modifier.padding(vertical = 10.dp),
                            fontFamily = FontFamily.Serif,
                            style = MaterialTheme.typography.h5
                        )
                    }
                }
            }
        }

        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
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
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun CoinDetailsSection(
    detailsName: String,
    detailsValue: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .then(modifier),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = detailsName,
            fontFamily = FontFamily.Serif,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = detailsValue,
            fontFamily = FontFamily.Serif,
            style = MaterialTheme.typography.h6
        )
    }
}