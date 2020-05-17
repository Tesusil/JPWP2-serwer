package com.tesusil.jezyki.wysokiego.poziomu.product

import com.tesusil.jezyki.wysokiego.poziomu.category.Category
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import javax.persistence.*

@Entity
data class Product(@Id @GeneratedValue(strategy = GenerationType.TABLE) val id: Long = 0,
                   @NotNull val name: String = "",
                   @NotNull val price: Double = 1.0,
                   @NotNull @ManyToOne @JoinColumn(name = "category_id", referencedColumnName = "id") val category: Category,
                   @Nullable val imgUrl: String? = null) {
}