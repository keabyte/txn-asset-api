package com.keabyte.transaction_engine.asset_api

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(
    info = Info(
        title = "txn-asset-api",
        version = "0.0.1",
        description = "API for managing assets"
    )
)
@SpringBootApplication
class TxnAssetApiApplication

fun main(args: Array<String>) {
    runApplication<TxnAssetApiApplication>(*args)
}
