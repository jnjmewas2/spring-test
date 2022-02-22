package com.example.search.application.keyword

data class KeywordResponse(
    var keywords: Collection<KeywordItem> = emptyList()
) {
    constructor(keywordPairList: List<Pair<String?, Int>>) : this(
        keywords = keywordPairList.map(::KeywordItem)
    )
}

data class KeywordItem(
    var query: String,
    var count: Int
) {
    constructor(keywordPair: Pair<String?, Int>) : this(
        keywordPair.first!!,
        keywordPair.second
    )
}