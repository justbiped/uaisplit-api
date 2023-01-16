package com.biped.tosplit.operation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/operation")
class OperationController {

    @GetMapping
    fun listOperations(): ResponseEntity<String> {
        val operations = OperationRepository().bla()
        return ResponseEntity
            .ok()
            .body(operations.toString())
    }
}