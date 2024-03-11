package com.keabyte.transaction_engine.asset_api

import io.restassured.RestAssured
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class IntegrationTest {

    @LocalServerPort
    private val port: Int? = null

    @BeforeEach
    fun beforeEach() {
        RestAssured.baseURI = "http://localhost:$port"
    }

    companion object {
        @ServiceConnection
        @Container
        val postgres = PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
    }

    @AfterEach
    fun afterEach() {
        RestAssured.reset()
    }
}