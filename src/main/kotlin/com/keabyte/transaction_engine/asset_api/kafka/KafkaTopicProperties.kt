package com.keabyte.transaction_engine.asset_api.kafka

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "spring.kafka.producer.topic")
data class KafkaTopicProperties @ConstructorBinding constructor(
    val prices: TopicConfiguration
)

data class TopicConfiguration(
    val name: String,
    val partitions: Int,
    val replicas: Int
)