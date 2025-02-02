package com.team.innergrim.innergrimapi.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient

@Configuration
class WebClientConfig(
    @Value("\${chatbot.base-url}") private val baseUrl: String,
    @Value("\${discord.webhook-url}") private val discordWebhookUrl: String,
) {

    val httpClient = HttpClient.create()
        .followRedirect(true)  // 리디렉션 활성화

    @Bean
    fun chatbotWebClient(): WebClient {
        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .baseUrl(baseUrl)
            .defaultHeader("Content-Type", "application/json;charset=UTF-8")
            .build()
    }

    @Bean
    fun discordWebClient(): WebClient {
        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .baseUrl(discordWebhookUrl) // Discord 웹훅 URL을 기본 URL로 설정
            .defaultHeader("Content-Type", "application/json;charset=UTF-8")
            .build()
    }

}