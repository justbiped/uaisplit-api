package biped.works.tosplit.transaction.controller

import biped.works.tosplit.core.toLocalDate
import biped.works.tosplit.transaction.CreateTransactionUseCase
import biped.works.tosplit.transaction.ListTransactionsUseCase
import biped.works.tosplit.transaction.data.Transaction
import biped.works.tosplit.transaction.data.remote.TransactionRequest
import biped.works.tosplit.transaction.data.toDomain
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transaction")
class TransactionController(
    private val listTransactionsUseCase: ListTransactionsUseCase,
    private val saveTransactionUseCase: CreateTransactionUseCase
) {
    @GetMapping("/{entry}/{conclusion}")
    fun getTransactions(
        @PathVariable entry: String,
        @PathVariable conclusion: String
    ): ResponseEntity<List<Transaction>> {
        val transactions = listTransactionsUseCase(entry.toLocalDate(), conclusion.toLocalDate())
        return ResponseEntity.ok(transactions)
    }

    @PostMapping
    fun saveTransaction(@RequestBody transactionRequest: TransactionRequest) {
        saveTransactionUseCase(transactionRequest.toDomain())
    }
}