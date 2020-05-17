package com.tesusil.jezyki.wysokiego.poziomu.order.response

import org.jetbrains.annotations.NotNull
import java.util.*

data class OrderShortDetailsResponse(@NotNull val userName: String,
                                     @NotNull val date: Date,
                                     @NotNull val amount: Double,
                                     @NotNull val orderId: Long) {
}