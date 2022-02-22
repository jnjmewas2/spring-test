package com.example.search.config

import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.time.Duration
import java.util.concurrent.TimeUnit


@Configuration
class RestTemplateConfig {

    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate? {
        val httpClient: CloseableHttpClient = HttpClientBuilder.create()
            .setMaxConnTotal(120)
            .setMaxConnPerRoute(100)
            .setConnectionTimeToLive(5, TimeUnit.SECONDS)
            .build()
        val factory = HttpComponentsClientHttpRequestFactory()
        factory.httpClient = httpClient
        val bufferingClientHttpRequestFactory = BufferingClientHttpRequestFactory(factory)
        return restTemplateBuilder
            .requestFactory { bufferingClientHttpRequestFactory }
            .setConnectTimeout(Duration.ofMillis(5000))
            .setReadTimeout(Duration.ofMillis(5000))
            .build()
    }

}