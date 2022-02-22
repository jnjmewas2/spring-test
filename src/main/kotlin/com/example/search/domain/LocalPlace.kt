package com.example.search.domain

import com.example.search.infra.search.KakaoLocalSearchDto
import com.example.search.infra.search.KakaoLocalSearchItemDto
import com.example.search.infra.search.NaverLocalSearchItemDto

class LocalPlace() {
    var title: String = ""
    var address: String = ""
    var roadAddress: String = ""

    constructor(kakaoLocalSearchItemDto: KakaoLocalSearchItemDto) : this() {
        this.title = kakaoLocalSearchItemDto.placeName
        this.address = kakaoLocalSearchItemDto.addressName
        this.roadAddress = kakaoLocalSearchItemDto.roadAddressName
    }

    constructor(naverLocalSearchItemDto: NaverLocalSearchItemDto) : this() {
        this.title = naverLocalSearchItemDto.title
        this.address = naverLocalSearchItemDto.address
        this.roadAddress = naverLocalSearchItemDto.roadAddress
    }

    override fun toString(): String {
        return "LocalPlace(title='$title', address='$address', roadAddress='$roadAddress')"
    }

}