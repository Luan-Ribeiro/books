package com.estudo.books.service

import com.estudo.books.enums.AuthorStatus
import com.estudo.books.enums.BookStatus
import com.estudo.books.request.BookUpdateRequest
import com.estudo.books.exception.NotFoundException
import com.estudo.books.extension.toAuthorModel
import com.estudo.books.extension.toBookModel
import com.estudo.books.extension.toBookResponse
import com.estudo.books.model.AuthorModel
import com.estudo.books.repository.BookRepository
import com.estudo.books.request.BookRequest
import com.estudo.books.response.AuthorResponse
import com.estudo.books.response.BookResponse
import org.springframework.stereotype.Service
import java.awt.print.Book

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    fun getBook(id: Int): BookResponse {
        return bookRepository.findById(id).orElseThrow {
            throw NotFoundException(String.format("This Book not exist, id=$id"), "BK-0002")
        }
            .toBookResponse()
    }

    fun findBooksByStatus(status: BookStatus): List<BookResponse> {
        return bookRepository.findByStatus(status).map { book -> book.toBookResponse() }
    }

    fun createBook(bookBody: BookRequest, author: AuthorResponse): BookResponse {
        if (author.status == AuthorStatus.INACTIVE) {
            throw NotFoundException(String.format("This Book it's inactive, author_id=${author.id}"), "BK-0004")
        }
        return bookRepository.save(bookBody.toBookModel(author.toAuthorModel())).toBookResponse()
    }

    fun updateBook(bookBody: BookUpdateRequest, id: Int): BookResponse {
        val bookFound = bookRepository.findById(id).orElseThrow {
            throw NotFoundException(String.format("This Book not exist, id=$id"), "BK-0002")
        }
        return bookRepository.save(bookBody.toBookModel(id, bookFound)).toBookResponse()
    }

    fun listAllBooks(): List<BookResponse> {
        return bookRepository.findAll().map { book -> book.toBookResponse() }
    }

    fun inactivateBook(id: Int) {
        val book = bookRepository.findById(id).orElseThrow {
            throw NotFoundException(String.format("This Book not exist, id=$id"), "BK-0002")
        }
        if (book.status == BookStatus.INACTIVE || book.status == BookStatus.DELETED) {
            throw NotFoundException(String.format("This Book already is inactive or deleted, id=$id"), "BK-0005")
        }
        book.status = BookStatus.INACTIVE
        bookRepository.save(book)
    }

    fun deleteBookByCustomer(author: AuthorModel) {
        val books = bookRepository.findByAuthor(author)
        for (book in books) {
            book.status = BookStatus.DELETED
        }
        bookRepository.saveAll(books)
    }
}