package com.kelly.coinplace.presentation.single_coin_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun TagsSection(modifier: Modifier = Modifier, tags: List<String>) {
        FlowRow(
            mainAxisSpacing = 10.dp,
            crossAxisSpacing = 10.dp,
            modifier = modifier
        ) {
            tags.forEach {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(10.dp)
                        ),
                ) {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
}