package com.tesusil.jezyki.wysokiego.poziomu.category

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Category(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
                    val name: String,
                    var imageUrl: String = "",
                    var parentId: Long? = null,
                    var hasChildren: Boolean = false)