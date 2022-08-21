package com.kelly.coinplace.presentation.single_coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.kelly.coinplace.domain.model.CoinSingle

@Composable
fun SingleCoinTopBar(
    coinSingle: CoinSingle,
    modifier: Modifier = Modifier,
    onIconButtonClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = onIconButtonClick) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Navigate to previous screen"
            )
        }
        Text(
            text = coinSingle.name,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
        )
        Text(
            text = coinSingle.symbol,
            style = MaterialTheme.typography.subtitle2,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
        )
    }
}