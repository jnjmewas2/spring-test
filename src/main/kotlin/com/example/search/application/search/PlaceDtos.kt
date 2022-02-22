package com.example.search.application.search

import com.example.search.domain.SortedLocalPlace

data class PlaceResponse(
    var query: String = "",
    var places: List<PlaceItem> = emptyList()
) {
    constructor(sortedLocalPlaceList: List<SortedLocalPlace>, keyword: String) : this(
        query = keyword,
        places = sortedLocalPlaceList.map(::PlaceItem)
    )
}

data class PlaceItem(
    var title: String,
    var address: String,
    var readAddress: String,
    var count: Int
) {
    constructor(sortedLocalPlace: SortedLocalPlace) : this(
        sortedLocalPlace.localPlace.title,
        sortedLocalPlace.localPlace.address,
        sortedLocalPlace.localPlace.roadAddress,
        sortedLocalPlace.hitCount
    )
}
