package com.kelly.coinplace.presentation.single_coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kelly.coinplace.domain.model.CoinSingle

@Composable
fun CoinInfoSection(coinSingle: CoinSingle, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = 10.dp,
        shape = MaterialTheme.shapes.medium.copy(CornerSize(15.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Circulating Supply\n" +
                            "${coinSingle.circulatingSupply}"
                )
                Divider()
                Text(
                    text = "Market Cap\n" +
                            "${coinSingle.marketCap}"
                )
            }
            Divider(modifier = Modifier.fillMaxHeight())
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total Supply\n" +
                            "${coinSingle.circulatingSupply}"
                )
                Divider()
                Text(
                    text = "Max Supply\n" +
                            "${coinSingle.marketCap}"
                )
            }
        }
    }
}