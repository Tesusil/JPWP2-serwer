package com.tesusil.jezyki.wysokiego.poziomu.order

import com.tesusil.jezyki.wysokiego.poziomu.category.Category
import com.tesusil.jezyki.wysokiego.poziomu.user.User
import org.jetbrains.annotations.NotNull
import java.util.*
import javax.persistence.*

@Entity
data class Order(@Id @GeneratedValue(strategy = GenerationType.TABLE) val id: Long = 0,
                 @NotNull @ManyToOne @JoinColumn(name = "user_id", referencedColumnName = "id") val user: User,
                 @NotNull @Temporal(TemporalType.DATE) val date: Date,
                 @NotNull val cost: Double) {
}