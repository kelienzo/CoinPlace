package com.kelly.coinplace.domain.model

import com.kelly.coinplace.data.remote.dto.Team

data class CoinSingleDesc(
    val description: String? = "N/A",
    val developmentStatus: String? = "N/A",
    val error: String?,
    val firstDataAt: String? = "N/A",
    val hardwareWallet: Boolean,
    val id: String? = "N/A",
    val isActive: Boolean,
    val isNew: Boolean,
    val lastDataAt: String? = "N/A",
    val message: String? = "N/A",
    val name: String? = "N/A",
    val openSource: Boolean,
    val orgStructure: String? = "N/A",
    val rank: Int,
    val startedAt: String? = "N/A",
    val symbol: String? = "N/A",
    val tags: List<String>,
    val team: List<Team>,
    val type: String? = "N/A"
)
