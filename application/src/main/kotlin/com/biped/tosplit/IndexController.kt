package com.biped.tosplit

import com.biped.tosplit.auth.Authorized
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class IndexController {

    @Authorized
    @GetMapping
    fun home(): ResponseEntity<String> {
        return ResponseEntity.ok().body("Opaa")
    }
}