package com.kelly.coinplace.presentation.all_coins.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
    val iconAnimation = remember {
        Animatable(initialValue = -1f)
    }

    LaunchedEffect(key1 = System.currentTimeMillis()) {
        iconAnimation.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 1000
                    0.0f at 250 with FastOutLinearInEasing
                    1f at 500 with FastOutLinearInEasing
                    0.0f at 750 with FastOutLinearInEasing
                    -1f at 1000 with FastOutLinearInEasing
                },
                repeatMode = RepeatMode.Reverse
            )
        )
    }
    val showButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 1
        }
    }

//    val infiniteTransition = rememberInfiniteTransition()
//    val size by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 10f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(
//                durationMillis = 2000,
//            ),
//            repeatMode = RepeatMode.Reverse
//        )
//    )
//    val color by infiniteTransition.animateColor(
//        initialValue = Color.Green,
//        targetValue = Color.Red,
//        animationSpec = infiniteRepeatable(
//            animation = tween(1000, easing = LinearEasing),
//            repeatMode = RepeatMode.Reverse
//        )
//    )

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
                directionValue = iconAnimation.value
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
    directionValue: Float,
    distance: Dp = 15.dp,
    modifier: Modifier = Modifier
) {
    val travelDirection = with(LocalDensity.current) {distance.toPx()}

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
            modifier = Modifier
                .size(30.dp)
                .graphicsLayer {
              translationY =  directionValue * travelDirection
            },
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "Move to the first item on the list",
        )
    }
}