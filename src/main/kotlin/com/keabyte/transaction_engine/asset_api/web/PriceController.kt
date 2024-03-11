package com.keabyte.transaction_engine.asset_api.web

import com.keabyte.transaction_engine.asset_api.service.PriceService
import com.keabyte.transaction_engine.asset_api.web.model.CreatePriceRequest
import com.keabyte.transaction_engine.asset_api.web.model.Price
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class PriceController(private val priceService: PriceService) {

    @PostMapping("/assets/{assetCode}/prices")
    fun createPrice(@PathVariable assetCode: String, @RequestBody request: CreatePriceRequest): Price {
        return priceService.createPrice(request.withAssetCode(assetCode)).toModel()
    }

    @GetMapping("/assets/{assetCode}/prices/latest")
    fun getLatestPrice(@PathVariable assetCode: String): Price {
        return priceService.getLatestPriceForAsset(assetCode).toModel()
    }
}
