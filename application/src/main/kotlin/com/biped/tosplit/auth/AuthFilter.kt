package com.biped.tosplit.auth

import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthFilter constructor(private val authorization: Authorization) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val accessToken = request.getHeader(AUTHORIZATION_HEADER_KEY)
        try {
            val uid = authorization.authorize(accessToken)
            request.setAttribute(UID_KEY, uid)
            filterChain.doFilter(request, response)
        } catch (error: Throwable) {
            throw UnauthorizedException()
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.requestURI.matches(Regex("/|/index|/health"))

    }

    companion object {
        const val AUTHORIZATION_HEADER_KEY = "Authorization"
        const val UID_KEY = "uid"
    }
}