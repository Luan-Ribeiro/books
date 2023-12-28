package com.estudo.books.response

import com.estudo.books.enums.BookStatus
import com.estudo.books.model.AuthorModel
import java.sql.Date

class BookResponse(
    var id: Int,
    var name: String,
    var status: BookStatus,
    var createdAt: Date,
    var author: AuthorModel,
)