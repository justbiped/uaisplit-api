package com.biped.tosplit.core

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class Configuration(private val environment: Environment) {

    @Bean
    fun setupFirebase() {
        val credentialsFileName = environment.getProperty("com.biped.firebase.file") ?: ""
        val firebaseCredentialsStream = this.javaClass.classLoader.getResourceAsStream(credentialsFileName)
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(firebaseCredentialsStream))
            .build()

        FirebaseApp.initializeApp(options)
    }
}
