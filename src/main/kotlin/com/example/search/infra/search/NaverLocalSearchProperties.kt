package com.example.search.infra.search

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("naver")
@ConstructorBinding
data class NaverLocalSearchProperties(val clientId: String, val secretKey: String, val apiUrl: String)
