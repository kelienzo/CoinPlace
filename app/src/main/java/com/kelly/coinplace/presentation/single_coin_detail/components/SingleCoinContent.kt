package com.kelly.coinplace.presentation.single_coin_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.kelly.coinplace.data.remote.dto.Team
import com.kelly.coinplace.domain.model.CoinSingle
import com.kelly.coinplace.domain.model.CoinSingleDesc

@Composable
fun SingleCoinContents(
    modifier: Modifier = Modifier,
    coinSingle: CoinSingle,
    coinSingleDesc: CoinSingleDesc
) {
    Column(
        modifier = modifier
    ) {
        CoinDescription(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            coinSingleDesc = coinSingleDesc
        )
        Spacer(modifier = Modifier.height(5.dp))
        TagsSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            tags = coinSingleDesc.tags
        )
        Spacer(modifier = Modifier.height(5.dp))
        CoinInfoSection(
            coinSingle = coinSingle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(3.dp)
        ) {
            items(coinSingleDesc.team) { team ->
                TeamsListItem(
                    team = team,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                Divider()
            }
        }
    }
}

@Composable
fun CoinDescription(modifier: Modifier = Modifier, coinSingleDesc: CoinSingleDesc) {
    Card(
        modifier = modifier,
        elevation = 10.dp,
        shape = MaterialTheme.shapes.large.copy(CornerSize(15.dp))
    ) {
        Text(
            text = coinSingleDesc.description ?: "",
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TeamsListItem(team: Team, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = team.name
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = team.position
        )
    }
}