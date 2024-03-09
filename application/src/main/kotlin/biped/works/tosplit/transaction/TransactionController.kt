package biped.works.tosplit.transaction

import biped.works.tosplit.core.toLocalDate
import biped.works.tosplit.transaction.data.domain.Transaction
import biped.works.tosplit.transaction.data.remote.TransactionRequest
import biped.works.tosplit.transaction.data.remote.TransactionUpdateRequest
import biped.works.tosplit.transaction.data.toDomain
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/transaction")
class TransactionController(
    private val listTransactionsUseCase: ListTransactionsUseCase,
    private val saveTransactionUseCase: CreateTransactionUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase
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
    fun createTransaction(@RequestBody transactionRequest: TransactionRequest): ResponseEntity<String> {
        // todo: create headers to get the client timezone
        saveTransactionUseCase(transactionRequest.toDomain())
        // todo: return created transaction
        return ResponseEntity.created(URI("")).build()
    }

    @PutMapping
    fun updateTransaction(@RequestBody updateRequest: TransactionUpdateRequest): ResponseEntity<String> {
        // edit a transaction here.
        val transaction = updateRequest.transaction.toDomain()
        val policy = UpdatePolicy.valueOf(updateRequest.policy)
        val result = updateTransactionUseCase(transaction, policy)
        return if (result.isSuccess) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.badRequest().build()
        }
    }
}