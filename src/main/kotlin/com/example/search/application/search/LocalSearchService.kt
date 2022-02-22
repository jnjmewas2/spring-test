package com.example.search.application.search

import com.example.search.application.keyword.KeywordService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import com.example.search.utils.logger
import java.util.concurrent.CompletableFuture

@Service
class LocalSearchService(
    @Qualifier("kakao_local_search") private val kakaoLocalSearch: LocalSearchProvider,
    @Qualifier("naver_local_search") private val naverLocalSearch: LocalSearchProvider,
    private val localPlaceSortService: LocalPlaceSortService,
    private val keywordService: KeywordService
) {
    val logger = logger()

    fun searchKeyword(keyword: String?): PlaceResponse {
        require(keyword != null && keyword != "") {
            logger.error("Keyword Not Found")
            throw KeywordNotFoundException("키워드가 없습니다. keyword:${keyword}")
        }

        keywordService.saveKeyword(keyword)

        val kakaoLocalPlaceList = kakaoLocalSearch.search(keyword)
        val naverLocalPlaceList = naverLocalSearch.search(keyword)

        CompletableFuture.allOf(kakaoLocalPlaceList, naverLocalPlaceList).join()

        logger.debug("kakao 결과: ${kakaoLocalPlaceList.get()}")
        logger.debug("naver 결과: ${naverLocalPlaceList.get()}")

       return PlaceResponse(localPlaceSortService.sort(kakaoLocalPlaceList.get(), naverLocalPlaceList.get()), keyword)
    }
}