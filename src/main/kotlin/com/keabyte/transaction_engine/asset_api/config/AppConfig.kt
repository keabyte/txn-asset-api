package com.keabyte.transaction_engine.asset_api.config

import com.keabyte.transaction_engine.asset_api.kafka.KafkaTopicProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(KafkaTopicProperties::class)
class AppConfig