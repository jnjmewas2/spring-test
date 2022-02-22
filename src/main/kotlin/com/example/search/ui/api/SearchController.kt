package com.example.search.ui.api

import com.example.search.application.search.LocalSearchService
import com.example.search.application.search.PlaceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SearchController {

    @Autowired
    private lateinit var localSearchService: LocalSearchService

    @RequestMapping(path = arrayOf("/v1/place"), method = arrayOf(RequestMethod.GET, RequestMethod.POST) )
    fun index(@RequestParam(required = false, value = "q") query: String?): ResponseEntity<ApiResponse<PlaceResponse>> {
        val placeResponse = localSearchService.searchKeyword(query)
        return ResponseEntity.ok(ApiResponse.success(placeResponse))
    }

}