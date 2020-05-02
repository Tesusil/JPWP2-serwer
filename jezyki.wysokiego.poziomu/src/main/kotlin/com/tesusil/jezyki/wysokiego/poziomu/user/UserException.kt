package com.tesusil.jezyki.wysokiego.poziomu.user

import java.lang.Exception
import javax.jws.soap.SOAPBinding

sealed class UserException : Exception() {

    open val code: Int = 400

    class UserNotFoundException() : UserException() {

        override val message: String?
            get() = "Not found user"
    }

    class UserNameNotAvailable() : UserException() {

        override val message: String?
            get() = "User name is not available"
    }

    sealed class PasswordException() : UserException() {
        class PasswordToShortException() : PasswordException() {
            override val message: String?
                get() = "Password is to short"
        }

        class PasswordContainsWrongChars() : PasswordException() {
            override val message: String?
                get() = "Password contains forbidden chars"
        }
    }
}