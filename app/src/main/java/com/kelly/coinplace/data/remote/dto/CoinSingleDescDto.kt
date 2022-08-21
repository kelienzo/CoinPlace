package com.kelly.coinplace.data.remote.dto

import com.kelly.coinplace.domain.model.CoinSingleDesc

data class CoinSingleDescDto(
    val description: String? = "N/A",
    val development_status: String? = "N/A",
    val error: String? = "N/A",
    val first_data_at: String? = "N/A",
    val hardware_wallet: Boolean,
    val hash_algorithm: String? = "N/A",
    val id: String? = "N/A",
    val is_active: Boolean,
    val is_new: Boolean,
    val last_data_at: String? = "N/A",
    val links: Links,
    val links_extended: List<LinksExtended>,
    val message: String? = "N/A",
    val name: String? = "N/A",
    val open_source: Boolean,
    val org_structure: String? = "N/A",
    val proof_type: String? = "N/A",
    val rank: Int,
    val started_at: String? = null,
    val symbol: String? = "N/A",
    val tags: List<Tag>,
    val team: List<Team>,
    val type: String? = "N/A",
    val whitepaper: Whitepaper
)

data class Links(
    val explorer: List<String>,
    val facebook: List<String>,
    val reddit: List<String>,
    val source_code: List<String>,
    val website: List<String>,
    val youtube: List<String>
)

data class LinksExtended(
    val stats: Stats,
    val type: String,
    val url: String
)

data class Stats(
    val contributors: Int,
    val followers: Int,
    val stars: Int,
    val subscribers: Int
)

data class Tag(
    val coin_counter: Int,
    val ico_counter: Int,
    val id: String,
    val name: String
)

data class Team(
    val id: String,
    val name: String,
    val position: String
)

data class Whitepaper(
    val link: String,
    val thumbnail: String
)

fun CoinSingleDescDto.toCoinSingleDesc(): CoinSingleDesc {
    return CoinSingleDesc(
        description = description,
        developmentStatus = development_status,
        error = error,
        firstDataAt = first_data_at,
        hardwareWallet = hardware_wallet,
        id = id,
        isActive = is_active,
        isNew = is_new,
        lastDataAt = last_data_at,
        message = message,
        name = name,
        openSource = open_source,
        orgStructure = org_structure,
        rank = rank,
        startedAt = started_at,
        symbol = symbol,
        tags = tags.map { it.name },
        team = team,
        type = type
    )
}