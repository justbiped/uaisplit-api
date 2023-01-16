package com.biped.tosplit.auth

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment


@Configuration
class AuthConfig(private val environment: Environment) {

    @Bean
    fun provideAuthorization() = Authorization()

//    @Configuration
//    class AuthFilterConfig {
//
//        @Bean
//        fun loggingFilter(authFilter: AuthFilter): FilterRegistrationBean<AuthFilter> {
//            val registrationBean = FilterRegistrationBean<AuthFilter>()
//            registrationBean.filter = authFilter
//            registrationBean.order = 1
//            registrationBean.addUrlPatterns("*")
//            return registrationBean
//        }
//    }
}