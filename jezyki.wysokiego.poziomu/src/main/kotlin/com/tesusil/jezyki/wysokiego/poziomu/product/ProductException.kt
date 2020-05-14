package com.tesusil.jezyki.wysokiego.poziomu.product

import java.lang.Exception

sealed class ProductException(private val messageException: String) : Exception(messageException) {

    class NotFound(private val id: Long) : ProductException("Not found product with id: $id")

    class ProductAlreadyExist(private val product: Product) : ProductException("Product with name: ${product.name} already exist!")
}