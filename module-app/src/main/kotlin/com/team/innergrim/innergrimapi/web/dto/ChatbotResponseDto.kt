package com.team.innergrim.innergrimapi.web.dto

import io.swagger.v3.oas.annotations.media.Schema

class ChatbotResponseDto {

    data class SendChat(
        @field:Schema(description = "answer")
        val answer: String?,
    ) {}

}