package com.example.homeworkme.config

import com.example.homeworkme.properties.BotProperties
import com.google.common.cache.CacheBuilder
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class CacheConfig(
    private val properties: BotProperties
) {

    @Bean("habrCacheManager")
    fun cacheManager(): CacheManager? {
        return object : ConcurrentMapCacheManager() {

            override fun createConcurrentMapCache(name: String): Cache {
                val cache: com.google.common.cache.Cache<Any, Any> = CacheBuilder.newBuilder()
                    .expireAfterWrite(properties.ttl)
                    .build()

                return ConcurrentMapCache(
                    name,
                    cache.asMap(),
                    false
                )
            }
        }
    }
}