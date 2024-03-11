package com.keabyte.transaction_engine.asset_api.web

import com.keabyte.transaction_engine.asset_api.IntegrationTest
import com.keabyte.transaction_engine.asset_api.repository.AssetRepository
import com.keabyte.transaction_engine.asset_api.repository.PriceRepository
import com.keabyte.transaction_engine.asset_api.repository.entity.AssetEntity
import com.keabyte.transaction_engine.asset_api.type.AssetType
import com.keabyte.transaction_engine.asset_api.web.model.CreatePriceRequest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal
import java.time.OffsetDateTime

class PriceControllerTest : IntegrationTest() {

    @Autowired
    lateinit var priceRepository: PriceRepository

    @Autowired
    lateinit var assetRepository: AssetRepository

    @Test
    fun `get latest price when no price exists`() {
        given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("/assets/MSTR/prices/latest")
            .then()
            .statusCode(500)
            .extract()
            .body()
    }

    @Test
    fun `create price`() {
        assetRepository.save(
            AssetEntity(
                assetCode = "ETH",
                name = "Ethereum",
                type = AssetType.CRYPTO,
                dividendYield = BigDecimal.ZERO,
                description = "Ethereum is a cryptocurrency",
                roundingScale = 8,
            )
        )

        val request = CreatePriceRequest(
            assetCode = "ETH",
            price = BigDecimal("3000.00"),
            currency = "USD",
            effectiveDate = OffsetDateTime.now()
        )

        given()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/assets/ETH/prices")
            .then()
            .statusCode(200)

        val price = priceRepository.findLatestPriceForAsset("ETH")
        assertThat(price).isPresent
    }
}