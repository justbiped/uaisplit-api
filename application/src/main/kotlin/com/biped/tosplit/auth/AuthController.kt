package com.biped.tosplit.auth

import com.biped.tosplit.auth.AuthFilter.Companion.UID_KEY
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authorize")
class AuthController {

    @GetMapping
    fun opa(@RequestAttribute(UID_KEY) uid: String): ResponseEntity<String> {
        return ResponseEntity.ok().body("Ta livre")
    }
}