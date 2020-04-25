package com.tesusil.jezyki.wysokiego.poziomu.category

import com.tesusil.jezyki.wysokiego.poziomu.category.request.NewCategoryRequest

fun categoryFromNewCategory(newCategory: NewCategoryRequest): Category {
   return Category(name = newCategory.name,
            imageUrl = newCategory.imageUrl,
            parentId = newCategory.parentId)
}