package com.example.search.infra.search

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverLocalSearchDto(
    @JsonProperty("items")
    val items: List<NaverLocalSearchItemDto>
)

data class NaverLocalSearchItemDto(
    var title: String,
    var address: String,
    var roadAddress: String
)
