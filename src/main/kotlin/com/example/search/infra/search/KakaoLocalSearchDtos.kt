package com.example.search.infra.search

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoLocalSearchDto(
    @JsonProperty("documents")
    val documents: List<KakaoLocalSearchItemDto>
)

data class KakaoLocalSearchItemDto(
    @JsonProperty("place_name")
    var placeName: String,
    @JsonProperty("address_name")
    var addressName: String,
    @JsonProperty("road_address_name")
    var roadAddressName: String
)
