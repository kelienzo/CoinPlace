package com.kelly.coinplace.domain.model

data class CoinSingle(
    val id: String,
    val athDate: String? = "N/A",
    val athPrice: Double? = 0.0,
    val circulatingSupply: Long,
    val error: String?,
    val firstDataAt: String,
    val lastUpdated: String,
    val marketCap: Long,
    val maxSupply: Long,
    val name: String,
    val price: Double,
    val percentageChange1h: Double,
    val rank: Int,
    val symbol: String,
    val totalSupply: Long
)
