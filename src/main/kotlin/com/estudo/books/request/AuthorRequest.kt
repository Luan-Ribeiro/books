package com.estudo.books.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class AuthorRequest(
    @field:NotEmpty(message = "name can't be empty")
    var name: String,

    @field:Email(message = "Email need be valid")
    var email: String,

    @field:NotEmpty(message = "Password can't be empty")
    var password: String,
)