package com.keabyte.transaction_engine.asset_api.repository.entity

import com.keabyte.transaction_engine.asset_api.web.model.Price
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SourceType
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity(name = "price")
data class PriceEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @CreationTimestamp(source = SourceType.DB)
    val createdDate: OffsetDateTime? = null,
    @CreationTimestamp(source = SourceType.DB)
    val effectiveDate: OffsetDateTime? = null,
    @ManyToOne
    @JoinColumn(name = "asset_id")
    val asset: AssetEntity,
    val price: BigDecimal,
    val currency: String
) {

    fun toModel() = Price(
        effectiveDate = effectiveDate.toString(),
        price = price,
        currency = currency,
        assetCode = asset.assetCode
    )
}