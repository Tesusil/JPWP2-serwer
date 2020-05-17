package com.tesusil.jezyki.wysokiego.poziomu.order.response

import com.tesusil.jezyki.wysokiego.poziomu.order.Order
import com.tesusil.jezyki.wysokiego.poziomu.orderItem.OrderItem

class OrderFullDetailsResponse(val order: OrderShortDetailsResponse, val productList: List<OrderItem>) {
}