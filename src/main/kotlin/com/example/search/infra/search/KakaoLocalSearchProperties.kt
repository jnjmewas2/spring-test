package com.example.search.infra.search

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("kakao")
@ConstructorBinding
data class KakaoLocalSearchProperties(val apiKey: String, val apiUrl: String)
