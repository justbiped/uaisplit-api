package com.biped.tosplit.auth

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class Authorization(credentialsFile: String = "") {

    init {
        val firebaseCredentialsStream = this.javaClass.classLoader.getResourceAsStream(credentialsFile)

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(firebaseCredentialsStream))
            .build()

        FirebaseApp.initializeApp(options)
    }

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

