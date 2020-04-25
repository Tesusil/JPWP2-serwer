package com.tesusil.jezyki.wysokiego.poziomu.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserInterface: JpaRepository<User, Long> {
}