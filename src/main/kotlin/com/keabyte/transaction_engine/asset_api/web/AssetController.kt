package com.keabyte.transaction_engine.asset_api.web

import com.keabyte.transaction_engine.asset_api.service.AssetService
import com.keabyte.transaction_engine.asset_api.web.model.Asset
import com.keabyte.transaction_engine.asset_api.web.model.CreateAssetRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/assets")
class AssetController(private val assetService: AssetService) {

    @GetMapping("/{assetCode}")
    fun findByAssetCode(@PathVariable assetCode: String): Asset {
        return assetService.findByAssetCode(assetCode).toModel()
    }

    @PostMapping
    fun createAsset(request: CreateAssetRequest): Asset {
        return assetService.createAsset(request).toModel()
    }
}