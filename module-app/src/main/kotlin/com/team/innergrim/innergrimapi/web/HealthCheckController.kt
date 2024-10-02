package com.team.innergrim.innergrimapi.web

import com.team.innergrim.innergrimapi.service.HealthCheckService
import com.team.innergrim.innergrimapi.service.MemberService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/health")
@RestController
@Tag(name = "health")
class HealthCheckController(
    private val healthCheckService: HealthCheckService
) {

    @GetMapping
    fun healthCheck () : String {
        return healthCheckService.getHealthCheck()
    }
}