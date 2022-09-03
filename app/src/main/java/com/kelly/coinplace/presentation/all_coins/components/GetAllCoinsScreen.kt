package com.kelly.coinplace.presentation.all_coins.components

import android.app.Application
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kelly.coinplace.presentation.all_coins.GetAllCoinsViewModel
import com.kelly.coinplace.presentation.screens.CoinPlaceScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GetAllCoins(navController: NavController, viewModel: GetAllCoinsViewModel = hiltViewModel()) {
    val getAllCoinsState = viewModel.getAllCoinsState.value
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val showButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 1
        }
    }
    val value by animateDpAsState(
        targetValue = 50.dp, animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    val infiniteTransition = rememberInfiniteTransition()
    val size by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val color by infiniteTransition.animateColor(
        initialValue = Color.Green,
        targetValue = Color.Red,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = 3.dp)
        ) {
            items(getAllCoinsState.data) { coins ->
                AllCoinsItem(
                    coins = coins,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(CoinPlaceScreens.SingleCoinDetailScreen.route + "/${coins.id}")
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

        AnimatedVisibility(
            visible = showButton,
            enter = slideInHorizontally() + fadeIn(),
            exit = slideOutHorizontally() + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 25.dp, end = 15.dp)
        ) {
            FloatingActionButton(
                listState = lazyListState,
                coroutineScope = coroutineScope,
            )
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
fun FloatingActionButton(
    listState: LazyListState,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        onClick = {
            coroutineScope.launch {
                listState.animateScrollToItem(0)
            }
        }
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "Move to the first item on the list",
        )
    }
}