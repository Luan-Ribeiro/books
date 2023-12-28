package com.estudo.books.repository

import com.estudo.books.model.AuthorModel
import org.springframework.data.repository.CrudRepository

interface AuthorRepository : CrudRepository<AuthorModel,Int> {
    fun findByEmail(email: String): AuthorModel?
}