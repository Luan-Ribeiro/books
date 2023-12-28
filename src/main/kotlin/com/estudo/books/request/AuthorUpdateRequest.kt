package com.estudo.books.request

data class AuthorUpdateRequest(
    var id: Int?,
    var name: String?,
    var email: String?,
    val password: String?,
)