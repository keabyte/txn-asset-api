package com.keabyte.transaction_engine.asset_api.web

import com.keabyte.transaction_engine.asset_api.service.PriceService
import com.keabyte.transaction_engine.asset_api.web.model.CreatePriceRequest
import com.keabyte.transaction_engine.asset_api.web.model.Price
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/prices")
class PriceController(private val priceService: PriceService) {

    @PostMapping("/{assetCode}/prices")
    fun createPrice(assetCode: String, request: CreatePriceRequest): Price {
        return priceService.createPrice(request.withAssetCode(assetCode)).toModel()
    }

    @GetMapping("/{assetCode}/prices/latest")
    fun getLatestPrice(assetCode: String): Price {
        return priceService.getLatestPriceForAsset(assetCode).toModel()
    }
}
