package com.kelly.coinplace.domain.model

import com.kelly.coinplace.data.remote.dto.Team

data class CoinSingleDesc(
    val description: String,
    val developmentStatus: String,
    val error: String?,
    val firstDataAt: String,
    val hardwareWallet: Boolean,
    val id: String,
    val isActive: Boolean,
    val isNew: Boolean,
    val lastDataAt: String,
    val message: String,
    val name: String,
    val openSource: Boolean,
    val orgStructure: String,
    val rank: Int,
    val startedAt: String,
    val symbol: String,
    val tags: List<String>,
    val team: List<Team>,
    val type: String
)
