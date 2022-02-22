package com.example.search.application.search

import com.example.search.domain.LocalPlace
import java.util.concurrent.CompletableFuture

interface LocalSearchProvider {
    val COUNT:Int
    fun search(keyword: String): CompletableFuture<List<LocalPlace>>
}