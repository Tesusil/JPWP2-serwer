package com.tesusil.jezyki.wysokiego.poziomu.product.response

import com.tesusil.jezyki.wysokiego.poziomu.category.Category
import com.tesusil.jezyki.wysokiego.poziomu.product.Product

data class ProductsAndCategoryResponse(val category: Category, val productList: List<Product>) {
}