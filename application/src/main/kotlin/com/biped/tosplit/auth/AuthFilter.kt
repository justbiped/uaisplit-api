package com.biped.tosplit.auth

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//@Component
//class AuthFilter constructor(private val authorization: Authorization) : OncePerRequestFilter() {
//
//    private val ignoredEndpoints = setOf("/", "/index", "/health")
//
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        try {
//            val accessToken = request.getHeader(AUTHORIZATION_HEADER_KEY)
//            val uid = authorization.authorize(accessToken)
//            request.setAttribute(UID_KEY, uid)
//            filterChain.doFilter(request, response)
//        } catch (error: Throwable) {
//            val responseEntity = parseError(error)
//            response.status = response.status
//            response.outputStream.println(responseEntity.body)
//        }
//    }
//
//    private fun parseError(error: Throwable): ResponseEntity<String> {
//        return when (error) {
//            is UnauthorizedException -> ResponseEntity("Unauthorized: ${error.message}", HttpStatus.UNAUTHORIZED)
//            else -> ResponseEntity("Bad request: ${error.message}", HttpStatus.BAD_REQUEST)
//        }
//    }
//
//    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
//        val pattern = ignoredEndpoints.joinToString("|")
//        return request.requestURI.matches(Regex(pattern))
//
//    }
//
//    companion object {
//        const val AUTHORIZATION_HEADER_KEY = "Authorization"
//        const val UID_KEY = "uid"
//    }
//}