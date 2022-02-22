package com.example.search.ui.api

import com.example.search.application.keyword.KeywordResponse
import com.example.search.application.keyword.KeywordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class KeywordController {

    @Autowired
    private lateinit var keywordService: KeywordService

    @GetMapping("/v1/keyword")
    fun keyword(): ResponseEntity<ApiResponse<KeywordResponse>> {
        val keywordResponse = keywordService.selectHotKeyword()
        return ResponseEntity.ok(ApiResponse.success(keywordResponse))
    }

}