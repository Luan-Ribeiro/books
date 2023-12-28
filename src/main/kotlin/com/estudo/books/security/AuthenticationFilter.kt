package com.estudo.books.security

import com.estudo.books.exception.AuthenticationException
import com.estudo.books.repository.AuthorRepository
import com.estudo.books.request.LoginRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class AuthenticationFilter(
    private val authorRepository: AuthorRepository,
    authenticationManager: AuthenticationManager
) : UsernamePasswordAuthenticationFilter(authenticationManager) {
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
            val authorId = authorRepository.findByEmail(loginRequest.email)?.id
            val authToken = UsernamePasswordAuthenticationToken(authorId, loginRequest.password)
            return authenticationManager.authenticate(authToken)
        }catch (ex: Exception) {
            throw AuthenticationException("Authentication failed","BK-006")
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val id = (authResult as AuthorCustomDetails).id
        response.addHeader("Authorization","123456")
    }
}