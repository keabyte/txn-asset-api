package com.keabyte.transaction_engine.asset_api.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.TopicBuilder
import org.springframework.stereotype.Service

@Service
class KafkaTopicConfig(private val kafkaTopicProperties: KafkaTopicProperties) {

    @Bean
    fun assetTopic(): NewTopic {
        return TopicBuilder
            .name(kafkaTopicProperties.prices.name)
            .partitions(kafkaTopicProperties.prices.partitions)
            .replicas(kafkaTopicProperties.prices.replicas)
            .build()
    }
}