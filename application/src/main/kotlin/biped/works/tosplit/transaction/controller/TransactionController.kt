package biped.works.tosplit.transaction.controller

import biped.works.tosplit.transaction.CreateTransactionUseCase
import biped.works.tosplit.transaction.ListOperationsUseCase
import biped.works.tosplit.transaction.data.Operation
import biped.works.tosplit.transaction.data.TransactionRequest
import biped.works.tosplit.transaction.data.toDomain
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/operation")
class TransactionController(
    private val createTransaction: CreateTransactionUseCase,
    private val listOperations: ListOperationsUseCase
) {

    @GetMapping
    fun getOperations(): ResponseEntity<List<Operation>> {
        val operations = listOperations()
        return ResponseEntity.ok(operations)
    }

    @PostMapping
    fun createTransaction(@RequestBody transactionRequest: TransactionRequest) {
        createTransaction(transactionRequest.toDomain())
    }
}