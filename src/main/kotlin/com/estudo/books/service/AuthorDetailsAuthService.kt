package com.estudo.books.service

import com.estudo.books.exception.AuthenticationException
import com.estudo.books.repository.AuthorRepository
import com.estudo.books.security.AuthorCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorDetailsAuthService(
    private val authorRepository: AuthorRepository
) : UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val author =
            authorRepository.findById(id.toInt()).orElseThrow { AuthenticationException("Author not found", "BK-007") }
        return AuthorCustomDetails(author)
    }
}