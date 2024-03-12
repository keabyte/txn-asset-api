package com.keabyte.transaction_engine.asset_api.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.keabyte.transaction_engine.asset_api.web.model.Asset
import com.keabyte.transaction_engine.asset_api.web.model.Price
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service

@Service
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, String>, private val objectMapper: ObjectMapper) {
    private val log = KotlinLogging.logger {}

    fun send(topic: String, key: String, message: String): SendResult<String, String> {
        log.info { "Sending message to topic '$topic' with key '$key' and message $message" }
        return kafkaTemplate.send(topic, key, message).get()
    }

    fun sendAsset(asset: Asset): SendResult<String, String> {
        return send("assets", asset.assetCode, objectMapper.writeValueAsString(asset))
    }

    fun sendPrice(price: Price): SendResult<String, String> {
        return send("prices", price.assetCode, objectMapper.writeValueAsString(price))
    }
}