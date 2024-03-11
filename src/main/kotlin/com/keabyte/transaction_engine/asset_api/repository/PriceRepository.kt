package com.keabyte.transaction_engine.asset_api.repository

import com.keabyte.transaction_engine.asset_api.repository.entity.PriceEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PriceRepository : CrudRepository<PriceEntity, Long> {

    @Query("SELECT p FROM price p WHERE p.asset.assetCode = ?1 ORDER BY p.effectiveDate DESC")
    fun findLatestPriceForAsset(assetCode: String): Optional<PriceEntity>
}