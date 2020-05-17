package com.tesusil.jezyki.wysokiego.poziomu.order.request

import com.tesusil.jezyki.wysokiego.poziomu.orderItem.OrderItem
import com.tesusil.jezyki.wysokiego.poziomu.product.Product
import org.jetbrains.annotations.NotNull
import javax.validation.constraints.NotEmpty

class NewOrderRequest(@NotNull val userId: Long,
                      @NotNull @NotEmpty val productList: List<AmountOfProduct>) {
}

class AmountOfProduct(val product: Product, val amount: Int)