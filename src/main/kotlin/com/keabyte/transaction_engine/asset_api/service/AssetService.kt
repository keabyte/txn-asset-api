package com.keabyte.transaction_engine.asset_api.service

import com.keabyte.transaction_engine.asset_api.exception.BusinessException
import com.keabyte.transaction_engine.asset_api.kafka.KafkaProducer
import com.keabyte.transaction_engine.asset_api.repository.AssetRepository
import com.keabyte.transaction_engine.asset_api.repository.entity.AssetEntity
import com.keabyte.transaction_engine.asset_api.type.AssetType
import com.keabyte.transaction_engine.asset_api.web.model.CreateAssetRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AssetService(private val assetRepository: AssetRepository, private val kafkaProducer: KafkaProducer) {

    @Transactional
    fun findByAssetCode(assetCode: String): AssetEntity {
        return assetRepository.findByAssetCode(assetCode)
            .orElseThrow { throw BusinessException("No asset exists with asset code $assetCode") }
    }

    @Transactional
    fun createAsset(request: CreateAssetRequest): AssetEntity {
        val asset = assetRepository.save(request.toEntity())
        kafkaProducer.sendAsset(asset.toModel())
        return asset
    }

    @Transactional
    fun findCashAssetForCurrency(currency: String): AssetEntity {
        return assetRepository.findByTypeAndCurrency(AssetType.CASH, currency)
            .firstOrNull() ?: throw BusinessException("No cash asset exists for currency $currency")
    }
}