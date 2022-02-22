package com.example.search.application.keyword

import com.example.search.domain.Keyword
import com.example.search.domain.KeywordRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class KeywordService(
    private val keywordRepository: KeywordRepository
) {
    private val COUNT = 10

    @Transactional
    fun saveKeyword(text: String) {
        keywordRepository.save(Keyword(text = text))
    }

    fun selectHotKeyword(): KeywordResponse {
        val keywords = keywordRepository.findAll()
        val keywordGroup = keywords.groupingBy { it.text }.eachCount()
        val result = keywordGroup.toList().sortedByDescending { it.second }.take(COUNT)
        return KeywordResponse(result)
    }
}