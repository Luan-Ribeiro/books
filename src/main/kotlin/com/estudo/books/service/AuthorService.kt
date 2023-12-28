package com.estudo.books.service

import com.estudo.books.enums.AuthorStatus
import com.estudo.books.enums.Role
import com.estudo.books.exception.NotFoundException
import com.estudo.books.extension.toAuthorModel
import com.estudo.books.extension.toAuthorResponse
import com.estudo.books.repository.AuthorRepository
import com.estudo.books.request.AuthorRequest
import com.estudo.books.request.AuthorUpdateRequest
import com.estudo.books.response.AuthorResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthorService(
    private val authorRepository: AuthorRepository,
    private val bookService: BookService,
    private val bCrypt: BCryptPasswordEncoder
) {

    fun getById(id: Int): AuthorResponse {
        return authorRepository.findById(id).orElseThrow {
            throw NotFoundException(String.format("This Author not exist, id=$id"), "BK-0002")
        }
            .toAuthorResponse()
    }



    fun createAuthor(authorBody: AuthorRequest): AuthorResponse {
        val authorCopy = authorBody.toAuthorModel().copy(
            roles = setOf(Role.AUTHOR),
            password = bCrypt.encode(authorBody.password)
        )
        return authorRepository.save(authorCopy).toAuthorResponse()
    }

    fun updateAuthor(authorBody: AuthorUpdateRequest, id: Int): AuthorResponse {
        val authorFound = authorRepository.findById(id).orElseThrow{
            throw NotFoundException(String.format("This Author not exist, id=$id"), "BK-0002")
        }
        return authorRepository.save(authorBody.toAuthorModel(id, authorFound)).toAuthorResponse()
    }

    fun deleteAuthor(id: Int) {
        val author = authorRepository.findById(id).orElseThrow{
            throw NotFoundException(String.format("This Author not exist, id=$id"), "BK-0002")
        }
        if (author.status == AuthorStatus.INACTIVE) {
            throw NotFoundException(String.format("This Author already is inactive, id=$id"), "BK-0003")
        }
        bookService.deleteBookByCustomer(author)
        author.status = AuthorStatus.INACTIVE
        authorRepository.save(author)
    }
}