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