package com.example.search.interfaces

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    @GetMapping("/index.do")
    fun index(): String {
        return "hello world"
    }


}