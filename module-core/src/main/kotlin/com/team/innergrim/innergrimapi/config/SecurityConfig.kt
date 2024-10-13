package com.team.innergrim.innergrimapi.config

import com.team.innergrim.innergrimapi.service.CustomUserDetailService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val customUserDetailService: CustomUserDetailService
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // 사이트 위변조 요청 방지
            .csrf { it.disable() }
            .sessionManagement { sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/css/**",
                        "/js/**"
                    )
                    .permitAll()
                    .requestMatchers("/auth/member/login")
                    .permitAll() // 로그인 엔드포인트는 누구나 접근 가능
                    .requestMatchers(/*HttpMethod.POST,*/"/member") // 회원가입은 토큰 불필요
                    .permitAll()
                    .anyRequest().authenticated() // 나머지 요청은 인증 필요
            }
            .authenticationProvider(daoAuthenticationProvider())
            .userDetailsService(customUserDetailService)
        return http.build()
    }

    // DaoAuthenticationProvider 설정 추가
    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        daoAuthenticationProvider.setUserDetailsService(customUserDetailService)
        return daoAuthenticationProvider
    }

    // AuthenticationManager를 빈으로 등록
    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java)
            .authenticationProvider(daoAuthenticationProvider())
            .build()
    }

    // 비밀번호 인코더 (선택사항: 비밀번호를 암호화할 때 사용)
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//    }
}