package com.example.search.infra.search

import com.example.search.application.search.LocalSearchProvider
import com.example.search.domain.LocalPlace
import com.example.search.utils.logger
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import java.util.concurrent.CompletableFuture

@Component
@Qualifier("naver_local_search")
class NaverLocalSearch(
    private val searchApiProperties: NaverLocalSearchProperties,
    private val restTemplate: RestTemplate
) : LocalSearchProvider {
    val logger = logger()
    override val COUNT: Int = 5

    @Async("asyncExecutor")
    override fun search(keyword: String): CompletableFuture<List<LocalPlace>> {
        val result = try {
            val header = HttpHeaders()
            header.set("X-Naver-Client-Id", searchApiProperties.clientId)
            header.set("X-Naver-Client-Secret", searchApiProperties.secretKey)

            val requestEntity = HttpEntity<Map<String, Any>>(header)
            val url = searchApiProperties.apiUrl
            val uri : UriComponents
                    = UriComponentsBuilder.fromHttpUrl(url).queryParam("query", keyword).queryParam("display", COUNT).build()

            val responseEntity : ResponseEntity<Map<*, *>>
                    = restTemplate.exchange(uri.toString(), HttpMethod.GET, requestEntity, Map::class.java)

            val mapper = jacksonObjectMapper()
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

            var bodyStr = mapper.writeValueAsString(responseEntity.body)
            val data = mapper.readValue(bodyStr, NaverLocalSearchDto::class.java)

            data.items.map {
                val htmlRegex = Regex("<[^>]*>")
                it.title = htmlRegex.replace(it.title, "")
                LocalPlace(it)
            }.toList()
        } catch (exception: Exception) {
            logger.error(exception.message)
            logger.error(exception.stackTraceToString())
            emptyList<LocalPlace>()
        }
        return CompletableFuture.completedFuture(result)
    }
}