package biped.works.tosplit.transaction.controller

import biped.works.tosplit.transaction.ListTransactionsUseCase
import biped.works.tosplit.transaction.data.Transaction
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/transaction")
class TransactionController(
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

    private fun String.toLocalDate() = LocalDate.parse(this)
}