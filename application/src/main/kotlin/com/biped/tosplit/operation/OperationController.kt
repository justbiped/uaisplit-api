package com.biped.tosplit.operation

import com.biped.tosplit.operation.data.OperationRequest
import com.biped.tosplit.operation.data.toDomain
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

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

    @PostMapping
    fun createOperation(@RequestBody operation: OperationRequest) {
        operation.toDomain()
    }
}