package com.example.search.ui

import com.example.search.ui.api.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    @GetMapping("/index.do")
    fun index(): ResponseEntity<ApiResponse<String>> {
        return ResponseEntity.ok(ApiResponse.success("Hello World"))
    }

}