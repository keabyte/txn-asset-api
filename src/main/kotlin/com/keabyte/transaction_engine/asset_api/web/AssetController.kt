package com.keabyte.transaction_engine.asset_api.web

import com.keabyte.transaction_engine.asset_api.service.AssetService
import com.keabyte.transaction_engine.asset_api.web.model.Asset
import com.keabyte.transaction_engine.asset_api.web.model.CreateAssetRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Asset")
@RestController
@RequestMapping("/assets")
class AssetController(private val assetService: AssetService) {

    @GetMapping("/{assetCode}")
    fun findByAssetCode(@PathVariable assetCode: String): Asset {
        return assetService.findByAssetCode(assetCode).toModel()
    }

    @PostMapping
    fun createAsset(@RequestBody request: CreateAssetRequest): Asset {
        return assetService.createAsset(request).toModel()
    }
}