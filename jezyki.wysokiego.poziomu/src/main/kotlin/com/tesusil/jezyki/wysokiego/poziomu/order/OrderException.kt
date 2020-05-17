package com.tesusil.jezyki.wysokiego.poziomu.order

sealed class OrderException(errorMessage: String): Exception(errorMessage)  {

    class NotFound(orderId: Long) : OrderException("Not found order with id: $orderId")
}
