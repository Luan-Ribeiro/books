package com.estudo.books.exception

import java.lang.Exception

class BadRequestException(override val message : String, val errorCode: String) : Exception()