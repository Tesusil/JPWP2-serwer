package com.tesusil.jezyki.wysokiego.poziomu.category.request

import java.lang.Exception

sealed class CategoryException(private val errorMessage: String) : Exception(errorMessage) {
    class NotFound(private val id: Long) : CategoryException("Category with id $id is not exist")
}