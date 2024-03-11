package com.keabyte.transaction_engine.asset_api.service

import com.keabyte.transaction_engine.asset_api.exception.BusinessException
import com.keabyte.transaction_engine.asset_api.repository.PriceRepository
import com.keabyte.transaction_engine.asset_api.repository.entity.PriceEntity
import com.keabyte.transaction_engine.asset_api.web.model.CreatePriceRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PriceService(private val assetService: AssetService, private val priceRepository: PriceRepository) {

    @Transactional
    fun createPrice(request: CreatePriceRequest): PriceEntity {
        val asset = assetService.findByAssetCode(request.assetCode)
        val price = PriceEntity(
            asset = asset,
            effectiveDate = request.effectiveDate,
            price = request.price,
            currency = request.currency
        )
        return priceRepository.save(price)
    }

    @Transactional
    fun getLatestPriceForAsset(assetCode: String): PriceEntity {
        return priceRepository.findLatestPriceForAsset(assetCode)
            .orElseThrow { throw BusinessException("No price exists for asset with code $assetCode") }
    }
}