package biped.works.tosplit.core

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.core.env.Environment

@Order(0)
@Configuration
class Configuration(private val environment: Environment) {

    @Bean
    fun setupFirebaseApplication(): FirebaseApp {
        val credentialsFileName = environment.getProperty("com.biped.firebase.file") ?: ""
        val firebaseCredentialsStream = this.javaClass.classLoader.getResourceAsStream(credentialsFileName)
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(firebaseCredentialsStream))
            .build()

        return FirebaseApp.initializeApp(options)
    }

    @Bean
    fun provideFirestore(firebaseApp: FirebaseApp) = FirestoreClient.getFirestore(firebaseApp)

}
