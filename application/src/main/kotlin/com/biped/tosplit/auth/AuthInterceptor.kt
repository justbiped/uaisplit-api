package com.biped.tosplit.auth

import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor(private val authorization: Authorization) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (isAuthNotRequired(handler)) return true

        val accessToken = request.getHeader(AUTH_HEADER_KEY)
        authorization.authorize(accessToken)

        return true
    }

    private fun isAuthNotRequired(handler: Any): Boolean {
        return (handler as HandlerMethod).method.getAnnotation(Authorized::class.java) == null
    }

    companion object {
        const val AUTH_HEADER_KEY = "Authorization"
    }
}