package com.tesusil.jezyki.wysokiego.poziomu.product

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
}