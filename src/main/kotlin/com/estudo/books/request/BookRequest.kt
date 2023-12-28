package com.estudo.books.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.NotEmpty

data class BookRequest(
    @field:NotEmpty(message = "Name can't be empty")
    var name: String,

    @JsonAlias("author_id")
    var authorId: Int,
)