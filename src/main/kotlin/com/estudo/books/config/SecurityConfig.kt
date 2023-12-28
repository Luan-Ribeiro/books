package com.estudo.books.config

import com.estudo.books.repository.AuthorRepository
import com.estudo.books.service.AuthorDetailsAuthService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.WebSecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.stereotype.Component


@Component
@EnableWebSecurity
class SecurityConfig(
    private val authorRepository: AuthorRepository,
    private val authorAuthService: AuthorDetailsAuthService
): WebSecurityConfigurer<>{

    private val PUBLIC_MATCHERS = arrayOf<String>(
        "/authors",
        "/swagger-ui.html",
        "/v2/api-docs",
        "/swagger-resources/**"
    )



    @Bean(name = [BeanIds.AUTHENTICATION_MANAGER])
    @Throws(java.lang.Exception::class)
    fun authenticationManager(auth: AuthenticationManagerBuilder): AuthenticationManager {
        auth.userDetailsService(authorAuthService)
            .passwordEncoder(bCryptPasswordEncoder())
       return auth.build()
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authz ->
                authz
                    .anyRequest().authenticated()
            }
            .httpBasic(withDefaults())
        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity -> web.ignoring().requestMatchers(HttpMethod.POST,*PUBLIC_MATCHERS) }
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}