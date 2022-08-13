package com.kelly.coinplace.data.remote.dto

import com.kelly.coinplace.domain.model.Coins

data class CoinsDto(
    val beta_value: Double,
    val circulating_supply: Long,
    val first_data_at: String,
    val id: String,
    val last_updated: String,
    val max_supply: Long,
    val name: String,
    val quotes: Quotes,
    val rank: Int,
    val symbol: String,
    val total_supply: Long
)

data class Quotes(
    val USD: USD
)

data class USD(
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

fun CoinsDto.toCoins(): Coins {
    return Coins(
        id = id,
        lastUpdated = last_updated,
        marketCap = quotes.USD.market_cap,
        name = name,
        price = quotes.USD.price,
        rank = rank,
        symbol = symbol,
        totalSupply = total_supply
    )
}