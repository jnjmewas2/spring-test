package com.example.search.domain

class SortedLocalPlace(var hitCount: Int = 1, val localPlace: LocalPlace) {
    override fun toString(): String {
        return "SortedLocalPlace(hitCount=$hitCount, localPlace=$localPlace)"
    }
}