package com.estudo.books.controller

import com.estudo.books.enums.BookStatus
import com.estudo.books.request.BookRequest
import com.estudo.books.request.BookUpdateRequest
import com.estudo.books.response.BookResponse
import com.estudo.books.service.AuthorService
import com.estudo.books.service.BookService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController(
    private val bookService: BookService,
    private val authorService: AuthorService
) {

    @GetMapping("/{id}")
    fun getBook(@PathVariable id: Int): BookResponse? {
        return bookService.getBook(id)
    }

    @GetMapping("/all")
    fun listBooks(): List<BookResponse> {
        return bookService.listAllBooks()
    }

    @GetMapping("/status")
    fun listBooksByStatus(@RequestParam status: BookStatus): List<BookResponse> {
        return bookService.findBooksByStatus(status)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody @Valid bookBody: BookRequest): BookResponse? {
        val author = authorService.getById(bookBody.authorId)
        return bookService.createBook(bookBody, author)
    }

    @PatchMapping("/{id}")
    fun updateBook(@RequestBody bookBody: BookUpdateRequest, @PathVariable id: Int): BookResponse {
        return bookService.updateBook(bookBody, id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable id: Int) {
        return bookService.inactivateBook(id)
    }

}