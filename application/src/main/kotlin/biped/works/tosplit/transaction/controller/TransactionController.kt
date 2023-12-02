package biped.works.tosplit.transaction.controller

import biped.works.tosplit.transaction.CreateTransactionUseCase
import biped.works.tosplit.transaction.ListTransactionsUseCase
import biped.works.tosplit.transaction.data.Transaction
import biped.works.tosplit.transaction.data.remote.TransactionRequest
import biped.works.tosplit.transaction.data.toDomain
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/transaction")
class TransactionController(
    private val createTransaction: CreateTransactionUseCase,
    private val listTransactions: ListTransactionsUseCase
) {
    @GetMapping("/{entry}/{conclusion}")
    fun getTransactions(
        @PathVariable entry: String,
        @PathVariable conclusion: String
    ): ResponseEntity<List<Transaction>> {
        val transactions = listTransactions(entry.toLocalDate(), conclusion.toLocalDate())
        return ResponseEntity.ok(transactions)
    }

    @PostMapping
    fun createTransaction(@RequestBody transactionRequest: TransactionRequest) {
        createTransaction(transactionRequest.toDomain())
    }

    private fun String.toLocalDate() = LocalDate.parse(this)
}