package com.keabyte.transaction_engine.asset_api.web

import com.keabyte.transaction_engine.asset_api.IntegrationTest
import com.keabyte.transaction_engine.asset_api.repository.PriceRepository
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class PriceControllerTest : IntegrationTest() {

    @Autowired
    lateinit var priceRepository: PriceRepository

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
}