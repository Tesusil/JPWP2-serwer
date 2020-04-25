package com.tesusil.jezyki.wysokiego.poziomu.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
        val userName: String = "",
        val password: String = "1234",
        val userRole: UserRole = UserRole.USER
)


enum class UserRole {
    USER,
    ADMIN
}