package com.team.innergrim.innergrimapi.app.web.dto

import com.team.innergrim.innergrimapi.enums.ChatRole
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

class ChatbotRequestDto {

    data class SendChat (
        @field:Schema(description = "previousConversionList", required = true)
        @field:NotNull
        val previousConversionList: List<PreviousConversion>,

        @field:Schema(description = "question ", required = true)
        @field:NotNull
        val question : String,
    ) {

    }

    data class PreviousConversion(
        @field:Schema(description = "chat role", required = true)
        @field:NotNull
        val role: ChatRole,

        @field:Schema(description = "chat history ", required = true)
        @field:NotNull
        val content: String,
    ) {}

}