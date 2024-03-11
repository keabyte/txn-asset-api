package com.keabyte.transaction_engine.asset_api.web.model

import com.keabyte.transaction_engine.asset_api.repository.entity.AssetEntity
import com.keabyte.transaction_engine.asset_api.type.AssetType
import java.math.BigDecimal
import java.time.OffsetDateTime

data class CreateAssetRequest(
    val assetCode: String,
    val name: String,
    val foundedDate: OffsetDateTime? = null,
    val dividendYield: BigDecimal,
    val description: String,
    val websiteUrl: String? = null,
    val type: AssetType,
    val roundingScale: Int,
    val currency: String? = null
) {
    fun toEntity() = AssetEntity(
        assetCode = assetCode,
        name = name,
        foundedDate = foundedDate,
        dividendYield = dividendYield,
        description = description,
        websiteUrl = websiteUrl,
        type = type,
        roundingScale = roundingScale,
        currency = currency
    )
}
