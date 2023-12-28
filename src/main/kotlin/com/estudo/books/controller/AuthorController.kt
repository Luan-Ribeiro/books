package com.estudo.books.controller

import com.estudo.books.request.AuthorRequest
import com.estudo.books.request.AuthorUpdateRequest
import com.estudo.books.response.AuthorResponse
import com.estudo.books.service.AuthorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("authors")
class AuthorController(
    private val authorService: AuthorService
) {

    @GetMapping("/{id}")
    fun getAuthor(@PathVariable id: Int): AuthorResponse? {
        return authorService.getById(id)
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun createAuthor(@RequestBody @Valid authorBody: AuthorRequest): AuthorResponse? {
        return authorService.createAuthor(authorBody)
    }

    @PatchMapping("/{id}")
    fun updateAuthor(@RequestBody authorBody: AuthorUpdateRequest, @PathVariable id: Int): AuthorResponse {
        return authorService.updateAuthor(authorBody, id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAuthor(@PathVariable id: Int) {
        return authorService.deleteAuthor(id)
    }

}