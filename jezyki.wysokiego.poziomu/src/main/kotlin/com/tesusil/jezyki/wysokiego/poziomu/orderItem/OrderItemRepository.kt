package com.tesusil.jezyki.wysokiego.poziomu.orderItem

import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository: JpaRepository<OrderItem, Long> {
}