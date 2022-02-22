package com.example.search.infra.search

import com.example.search.application.search.LocalSearchProvider
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class KakaoLocalSearchTest {
    @Autowired
    @Qualifier("kakao_local_search")
    private lateinit var kakaoLocalSearch: LocalSearchProvider

    @Test
    fun getItems() {
        val keyword = "이매역 은행"
        val result = kakaoLocalSearch.search(keyword).get()
        assertNotNull(result)
        assertTrue(result.size > 0)
    }
}