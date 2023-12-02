package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.Transaction
import biped.works.tosplit.transaction.data.TransactionRepository
import biped.works.tosplit.transaction.data.Range
import java.time.LocalDate
import javax.inject.Inject

class ListTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    operator fun invoke(entry: LocalDate, conclusion: LocalDate): List<Transaction> {
        val range = Range(entry, conclusion)
        return transactionRepository
            .getTransactionMetadataList(range)
            .flatMap {
                it.createTransactions(range)
            }
    }
}