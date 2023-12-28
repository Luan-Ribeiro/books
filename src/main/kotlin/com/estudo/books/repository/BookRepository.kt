package com.estudo.books.repository

import com.estudo.books.enums.BookStatus
import com.estudo.books.model.AuthorModel
import com.estudo.books.model.BookModel
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookModel,Int> {
    fun findByAuthor(authorModel: AuthorModel): List<BookModel>
    fun findByStatus(status: BookStatus): List<BookModel>
}