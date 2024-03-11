package com.keabyte.transaction_engine.asset_api.repository

import com.keabyte.transaction_engine.asset_api.repository.entity.AssetEntity
import com.keabyte.transaction_engine.asset_api.type.AssetType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AssetRepository : CrudRepository<AssetEntity, Long> {

    fun findByTypeAndCurrency(type: AssetType, currency: String): List<AssetEntity>

    fun findByAssetCode(assetCode: String): Optional<AssetEntity>
}