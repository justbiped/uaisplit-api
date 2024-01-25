package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.TimeSpan
import biped.works.tosplit.transaction.data.TransactionRepository
import java.time.LocalDate
import javax.inject.Inject

class ListTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    operator fun invoke(entry: LocalDate, conclusion: LocalDate): List<Transaction> {
        val timeSpan = TimeSpan(entry, conclusion)
        return transactionRepository
            .getTransactionMetadataList(timeSpan)
            .map {
                Transaction("")
                //    it.createTransactions(timeSpan)
            }

    }
}

data class Transaction(val bla: String)