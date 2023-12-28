package com.estudo.books.request

import com.estudo.books.enums.BookStatus

data class BookUpdateRequest(
    var name: String?,
    var status: BookStatus?,
)