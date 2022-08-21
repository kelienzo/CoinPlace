package com.kelly.coinplace.data.remote.dto

import com.kelly.coinplace.domain.model.CoinSingle

data class CoinSingleDto(
    val beta_value: Double,
    val circulating_supply: Long,
    val error: String? = "N/A",
    val first_data_at: String,
    val id: String,
    val last_updated: String,
    val max_supply: Long,
    val name: String,
    val quotes: QuotesSingle,
    val rank: Int,
    val symbol: String,
    val total_supply: Long
)

data class QuotesSingle(
    val USD: USDSingle
)

data class USDSingle(
    val ath_date: String,
    val ath_price: Double,
    val market_cap: Long,
    val market_cap_change_24h: Double,
    val percent_change_12h: Double,
    val percent_change_15m: Double,
    val percent_change_1h: Double,
    val percent_change_1y: Double,
    val percent_change_24h: Double,
    val percent_change_30d: Double,
    val percent_change_30m: Double,
    val percent_change_6h: Double,
    val percent_change_7d: Double,
    val percent_from_price_ath: Double,
    val price: Double,
    val volume_24h: Double,
    val volume_24h_change_24h: Double
)

fun CoinSingleDto.toCoinSingle(): CoinSingle {
    return CoinSingle(
        id = id,
        athDate = quotes.USD.ath_date,
        athPrice = quotes.USD.ath_price,
        circulatingSupply = circulating_supply,
        error = error,
        firstDataAt = first_data_at,
        lastUpdated = last_updated,
        marketCap = quotes.USD.market_cap,
        maxSupply = max_supply,
        name = name,
        price = quotes.USD.price,
        percentageChange1h = quotes.USD.percent_change_1h,
        rank = rank,
        symbol = symbol,
        totalSupply = total_supply
    )
}