package com.keabyte.transaction_engine.asset_api.web

import com.keabyte.transaction_engine.asset_api.IntegrationTest
import com.keabyte.transaction_engine.asset_api.repository.AssetRepository
import com.keabyte.transaction_engine.asset_api.repository.entity.AssetEntity
import com.keabyte.transaction_engine.asset_api.type.AssetType
import com.keabyte.transaction_engine.asset_api.web.model.CreateAssetRequest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal

class AssetControllerTest : IntegrationTest() {

    @Autowired
    lateinit var assetRepository: AssetRepository

    @Test
    fun `get asset`() {
        val asset = AssetEntity(
            assetCode = "BTC",
            name = "Bitcoin",
            type = AssetType.CRYPTO,
            dividendYield = BigDecimal.ZERO,
            description = "Bitcoin is a cryptocurrency",
            roundingScale = 8,
        )
        assetRepository.save(asset)

        given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("/assets/BTC")
            .then()
            .statusCode(200)
    }

    @Test
    fun `get asset when no asset exists`() {
        given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("/assets/ABC")
            .then()
            .statusCode(500)
    }

    @Test
    fun `create asset`() {
        val request = CreateAssetRequest(
            assetCode = "SPY",
            name = "SPDR S&P 500 ETF Trust",
            type = AssetType.STOCK,
            dividendYield = BigDecimal("0.019"),
            description = "SPDR S&P 500 ETF Trust is an exchange-traded fund incorporated in the USA.",
            roundingScale = 2,
        )

        given()
            .contentType(ContentType.JSON)
            .`when`()
            .body(request)
            .post("/assets")
            .then()
            .statusCode(200)

        val asset = assetRepository.findByAssetCode("SPY")
        assertThat(asset).isPresent
    }
}