package com.kelly.coinplace.presentation.all_coins.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelly.coinplace.common.parseStringToDateAndFormatToString
import com.kelly.coinplace.domain.model.Coins
import java.text.DecimalFormat

@Composable
fun AllCoinsItem(coins: Coins, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small.copy(
            bottomStart = CornerSize(20.dp),
            topEnd = CornerSize(20.dp)
        ),
        elevation = 5.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .border(
                        BorderStroke(
                            width = 1.5.dp,
                            color = MaterialTheme.colors.primary
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clip(CircleShape)
                    .padding(10.dp)
                    .size(38.dp)
            ) {
                Text(
                    text = coins.symbol,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle2,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            CoinDetailsSection(
                coins = coins,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
        }
    }
}

@Composable
fun CoinDetailsSection(coins: Coins, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        var formattedAmount = ""
        try {
            formattedAmount = DecimalFormat("#,###,###,###.###").format(coins.price.toFloat())
        } catch (n: NumberFormatException) {
            n.printStackTrace()
        }
        CoinsDetails(
            firstText = coins.name,
            secondText = "${coins.rank}",
            thirdText = "Last Updated:\n ${coins.lastUpdated.parseStringToDateAndFormatToString("dd-MM-yyyy hh:mm:ss a")}",
            horizontalAlignment = Alignment.Start
        )
        CoinsDetails(
            firstText = "\$$formattedAmount",
            secondText = "Total Supply:\n ${coins.totalSupply}",
            thirdText = "Market Cap:\n ${coins.marketCap}",
            horizontalAlignment = Alignment.End
        )
    }
}

@Composable
fun CoinsDetails(
    firstText: String,
    secondText: String,
    thirdText: String,
    horizontalAlignment: Alignment.Horizontal,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = firstText,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            fontSize = 17.sp,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = secondText
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = thirdText
        )
    }
}