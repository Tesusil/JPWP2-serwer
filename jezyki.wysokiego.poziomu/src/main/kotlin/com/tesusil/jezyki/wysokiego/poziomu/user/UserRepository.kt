package com.tesusil.jezyki.wysokiego.poziomu.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}