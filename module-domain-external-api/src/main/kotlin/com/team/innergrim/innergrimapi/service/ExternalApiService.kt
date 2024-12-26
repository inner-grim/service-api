package com.team.innergrim.innergrimapi.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class ExternalApiService (
    private val webClient: WebClient
) {

    // ::::: [GET] :::::
    fun getData(): Mono<String> {
        val result = webClient.get()
            .uri("/fact") // baseUrl에 추가되는 경로
            .retrieve()
            .bodyToMono(String::class.java) // 원하는 반환 타입

        return result
    }

    // ::::: [POST] :::::
    fun <T : Any> postData(endpoint: String, requestBody: T): Mono<String> {
        return webClient.post()
            .uri(endpoint) // baseUrl에 추가되는 경로
            .bodyValue(requestBody) // 요청 본문 설정
            .retrieve()
            .bodyToMono(String::class.java) // 원하는 반환 타입
    }
}