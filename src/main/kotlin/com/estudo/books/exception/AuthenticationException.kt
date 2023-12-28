package com.estudo.books.exception

import java.lang.Exception

class AuthenticationException(override val message : String, val errorCode: String) : Exception()