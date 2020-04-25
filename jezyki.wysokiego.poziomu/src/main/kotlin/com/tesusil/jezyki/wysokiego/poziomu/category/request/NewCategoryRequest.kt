package com.tesusil.jezyki.wysokiego.poziomu.category.request

data class NewCategoryRequest(val name: String,
                              val imageUrl: String = "",
                              val parentId: Long? = null)