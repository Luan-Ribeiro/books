package com.estudo.books.response

import com.estudo.books.enums.AuthorStatus
import java.sql.Date

class AuthorResponse (
    var id: Int,
    var name: String,
    var email: String,
    var status: AuthorStatus,
    var createdAt: Date,
)