package com.estudo.books.extension

import com.estudo.books.enums.AuthorStatus
import com.estudo.books.enums.BookStatus
import com.estudo.books.model.AuthorModel
import com.estudo.books.request.BookUpdateRequest
import com.estudo.books.model.BookModel
import com.estudo.books.request.AuthorRequest
import com.estudo.books.request.AuthorUpdateRequest
import com.estudo.books.request.BookRequest
import com.estudo.books.response.AuthorResponse
import com.estudo.books.response.BookResponse

fun BookRequest.toBookModel(author: AuthorModel): BookModel {
    return BookModel(
        name = this.name,
        status = BookStatus.ACTIVE,
        author = author
    )
}

fun BookModel.toBookResponse(): BookResponse {
    return BookResponse(
        id = this.id!!,
        name = this.name,
        status = this.status,
        createdAt = this.createdAt!!,
        author = this.author!!
    )
}

fun BookUpdateRequest.toBookModel(id: Int, bookFound: BookModel): BookModel {
    return BookModel(
        id = id,
        name = this.name ?: bookFound.name,
        status = this.status ?: bookFound.status,
        createdAt = bookFound.createdAt,
        author = bookFound.author,
    )
}

fun AuthorModel.toAuthorResponse(): AuthorResponse {
    return AuthorResponse(
        id = this.id!!,
        name = this.name,
        email = this.email,
        status = this.status,
        createdAt = this.createdAt!!,
    )
}

fun AuthorRequest.toAuthorModel(): AuthorModel {
    return AuthorModel(
        name = this.name,
        email = this.email,
        password = this.password,
        status = AuthorStatus.ACTIVE
    )
}

fun AuthorUpdateRequest.toAuthorModel(id: Int, authorFound: AuthorModel): AuthorModel {
    return AuthorModel(
        id = id,
        name = this.name ?: authorFound.name,
        email = this.email ?: authorFound.email,
        password = this.password ?: authorFound.password,
        status = authorFound.status,
        createdAt = authorFound.createdAt

    )
}

fun AuthorResponse.toAuthorModel(): AuthorModel {
    return AuthorModel(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}