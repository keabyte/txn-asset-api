package com.keabyte.transaction_engine.asset_api.kafka

import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.TopicBuilder
import org.springframework.stereotype.Service

@Service
class KafkaTopicConfig(private val kafkaTopicProperties: KafkaTopicProperties) {

    @Bean
    fun priceTopic() = TopicBuilder
        .name(kafkaTopicProperties.prices.name)
        .partitions(kafkaTopicProperties.prices.partitions)
        .replicas(kafkaTopicProperties.prices.replicas)
        .build()

    @Bean
    fun assetTopic() = TopicBuilder
        .name(kafkaTopicProperties.assets.name)
        .partitions(kafkaTopicProperties.assets.partitions)
        .replicas(kafkaTopicProperties.assets.replicas)
        .build()
}