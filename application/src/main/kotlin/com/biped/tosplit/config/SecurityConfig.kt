package com.biped.tosplit.config

import com.biped.tosplit.auth.AuthFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig constructor() {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http

            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            .authorizeRequests()
            .antMatchers("/**").permitAll()
            .and()

            .build()
    }
}

//@Component
//class AuthFilter constructor(private val authorization: Authorization) : AbstractPreAuthenticatedProcessingFilter() {
//    init {
//        this.setAuthenticationManager { authentication ->
//            val accessToken = (authentication.principal as? String) ?: ""
//            try {
//                authorization.authorize(accessToken)
//                authentication.isAuthenticated = true
//            } catch (error: Throwable) {
//                throw BadCredentialsException(error.message)
//            }
//
//            authentication
//        }
//    }
//
//    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest): Any {
//        return request.getHeader(AUTHORIZATION_HEADER_KEY)
//    }
//
//    override fun getPreAuthenticatedCredentials(request: HttpServletRequest?): Any {
//        return "N/A"
//    }
//
//    companion object {
//        const val AUTHORIZATION_HEADER_KEY = "Authorization"
//    }
//}
//
//
