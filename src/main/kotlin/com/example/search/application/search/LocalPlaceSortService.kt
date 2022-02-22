package com.example.search.application.search

import com.example.search.domain.LocalPlace
import com.example.search.domain.SortedLocalPlace
import com.example.search.extensions.removeFirst
import org.springframework.stereotype.Service

@Service
class LocalPlaceSortService {

    fun sort(firstList: List<LocalPlace>, secondList: List<LocalPlace>): List<SortedLocalPlace> {
        var equalList = mutableListOf<LocalPlace>()
        var sortedLocalPlaceList = mutableListOf<SortedLocalPlace>()

        for (firstItem in firstList) {
            val sortedLocalPlace = SortedLocalPlace(localPlace = firstItem)

            for (secondItem in secondList) {
                if (firstItem.title == secondItem.title) {
                    sortedLocalPlace.hitCount++
                    equalList.add(secondItem)
                    break
                }

                if (firstItem.address.removeFirst() == secondItem.address.removeFirst()) {
                    sortedLocalPlace.hitCount++
                    equalList.add(secondItem)
                    break
                }

                if (firstItem.roadAddress.removeFirst() == secondItem.roadAddress.removeFirst()) {
                    sortedLocalPlace.hitCount++
                    equalList.add(secondItem)
                    break
                }
            }

            sortedLocalPlaceList.add(sortedLocalPlace)
        }

        sortedLocalPlaceList.sortByDescending { it.hitCount }

        for (secondItem in secondList) {
            var equal = false
            for (equalItem in equalList) {
                if (secondItem.title == equalItem.title && secondItem.address == equalItem.address
                    && secondItem.roadAddress == equalItem.roadAddress) {
                    equal = true
                }
            }

            if (equal == false) {
                sortedLocalPlaceList.add(SortedLocalPlace(localPlace = secondItem))
            }
        }

        return sortedLocalPlaceList
    }

}