package com.kelly.coinplace.domain.model

data class Coins(
    val id: String,
    val error: String = "",
    val lastUpdated: String,
    val marketCap: Long,
    val name: String,
    val price: Double,
    val rank: Int,
    val symbol: String,
    val totalSupply: Int
)