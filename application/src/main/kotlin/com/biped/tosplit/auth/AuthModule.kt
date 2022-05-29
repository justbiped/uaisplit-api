package com.biped.tosplit.auth

import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component


@Component
class AuthModule(private val environment: Environment) {

    @Bean
    fun provideAuthorization(): Authorization {
        val credentialsFileName = environment.getProperty("com.biped.firebase.file") ?: ""
        return Authorization(credentialsFileName)
    }
}