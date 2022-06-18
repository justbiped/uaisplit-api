package com.biped.tosplit.auth

import org.springframework.stereotype.Component
import javax.servlet.FilterChain
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


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


@Component
class AuthFilter constructor(private val authorization: Authorization) : HttpFilter() {

    override fun doFilter(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val accessToken = request.getHeader(AUTHORIZATION_HEADER_KEY)
        try {
            val uid = authorization.authorize(accessToken)

            request.setAttribute(UID_KEY, uid)
            chain.doFilter(request, response)
        } catch (error: Throwable) {
            throw UnauthorizedException()
        }

    }

    companion object {
        const val AUTHORIZATION_HEADER_KEY = "Authorization"
        const val UID_KEY = "uid"
    }
}