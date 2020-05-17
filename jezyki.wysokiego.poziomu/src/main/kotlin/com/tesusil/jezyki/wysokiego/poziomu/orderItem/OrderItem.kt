package com.tesusil.jezyki.wysokiego.poziomu.orderItem

import com.tesusil.jezyki.wysokiego.poziomu.order.Order
import com.tesusil.jezyki.wysokiego.poziomu.order.request.AmountOfProduct
import com.tesusil.jezyki.wysokiego.poziomu.product.Product
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
data class OrderItem(@Id @GeneratedValue(strategy = GenerationType.TABLE) val id: Long = 0,
                     @NotNull @ManyToOne @JoinColumn(name = "product_id", referencedColumnName = "id") val product: Product,
                     @NotNull val amount: Int,
                     @NotNull @ManyToOne @JoinColumn(name = "order_id", referencedColumnName = "id") val order: Order) {

    constructor(amountOfProduct: AmountOfProduct, order: Order): this(product =  amountOfProduct.product, amount = amountOfProduct.amount, order = order)
}