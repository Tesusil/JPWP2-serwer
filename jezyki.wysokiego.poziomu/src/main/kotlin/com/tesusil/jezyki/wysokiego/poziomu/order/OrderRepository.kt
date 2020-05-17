package com.tesusil.jezyki.wysokiego.poziomu.order

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}