package com.tesusil.jezyki.wysokiego.poziomu.user

import com.tesusil.jezyki.wysokiego.poziomu.user.request.LoginRequest
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user/")
class UserController(val repository: UserRepository) {

    @PostMapping("login")
    fun login(@RequestBody body: LoginRequest): User {
        return repository.findAll().asSequence().filter { it.userName == body.userName && it.password == it.password }.firstOrNull()
                ?: throw UserException.UserNotFoundException()
    }

    @PostMapping("register")
    fun register(@RequestBody body: User) {
        if (!isUserNameAvailable(body.userName))
            throw UserException.UserNotFoundException()
        try {
            isPasswordValid(body.password)
        } catch (e: UserException) {
            throw e
        }
        repository.save(body)
    }

    @GetMapping("userName")
    fun checkUserName(@RequestParam name: String): Boolean {
        return isUserNameAvailable(name)
    }

    private fun isUserNameAvailable(userName: String): Boolean {
        val count = repository.findAll().filter { it.userName == userName }.count()
        return count == 0
    }

    private fun isPasswordValid(password: String): Boolean {
        if (password.isBlank() || password.isEmpty())
            throw UserException.PasswordException.PasswordToShortException()

        val isSizeValid = password.length >= 4

        if (!isSizeValid)
            throw UserException.PasswordException.PasswordToShortException()

        password.forEach {
            if (!isCharValid(it))
                throw UserException.PasswordException.PasswordContainsWrongChars()
        }

        return true
    }

    private fun isCharValid(c: Char): Boolean {
        val value = c.toInt()
        return (value in 48..57) || (value in 65..90) || (value in 97..122)
    }
}