package com.biped.tosplit.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class Authorization {

    @Throws(UnauthorizedException::class, IllegalArgumentException::class, Throwable::class)
    fun authorize(idToken: String): String = try {
        FirebaseAuth.getInstance().verifyIdToken(idToken).uid
    } catch (error: Throwable) {
        if (error is FirebaseAuthException) {
            throw UnauthorizedException()
        }

        throw error
    }
}

