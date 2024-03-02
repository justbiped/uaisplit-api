package biped.works.tosplit.transaction.controller

import biped.works.tosplit.core.toLocalDate
import biped.works.tosplit.transaction.CreateTransactionUseCase
import biped.works.tosplit.transaction.EditTransactionUseCase
import biped.works.tosplit.transaction.ListTransactionsUseCase
import biped.works.tosplit.transaction.UpdatePolicy
import biped.works.tosplit.transaction.data.domain.Transaction
import biped.works.tosplit.transaction.data.remote.TransactionRequest
import biped.works.tosplit.transaction.data.remote.TransactionUpdateRequest
import biped.works.tosplit.transaction.data.toDomain
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transaction")
class TransactionController(
    private val listTransactionsUseCase: ListTransactionsUseCase,
    private val saveTransactionUseCase: CreateTransactionUseCase,
    private val editTransactionUseCase: EditTransactionUseCase
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
    fun createTransaction(@RequestBody transactionRequest: TransactionRequest) {
        // todo: create headers to get the client timezone
        saveTransactionUseCase(transactionRequest.toDomain())
        // todo: return created transaction
    }

    @PutMapping
    fun updateTransaction(@RequestBody updateRequest: TransactionUpdateRequest) {
        // edit a transaction here.
        val transaction = updateRequest.transactionRequest.toDomain()
        val policy = UpdatePolicy.valueOf(updateRequest.policy)
        editTransactionUseCase(transaction, policy)
    }
}