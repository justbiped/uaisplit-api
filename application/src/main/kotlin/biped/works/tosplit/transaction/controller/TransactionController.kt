package biped.works.tosplit.transaction.controller

import biped.works.tosplit.transaction.CreateOperationMetadataUseCase
import biped.works.tosplit.transaction.data.TransactionMetadataRepository
import biped.works.tosplit.transaction.data.TransactionRequest
import biped.works.tosplit.transaction.data.toDomain
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/transaction")
class TransactionController constructor(
    private val createOperationMetadata: CreateOperationMetadataUseCase
) {

    @GetMapping
    fun listOperations(): ResponseEntity<String> {
        val operations = TransactionMetadataRepository().bla()
        return ResponseEntity
            .ok()
            .body(operations.toString())
    }

    @PostMapping
    fun createTransaction(@RequestBody transactionRequest: TransactionRequest) {
        createOperationMetadata(transactionRequest.toDomain())
    }
}