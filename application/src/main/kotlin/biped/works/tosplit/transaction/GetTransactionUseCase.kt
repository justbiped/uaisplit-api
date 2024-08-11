package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.TransactionLocator
import biped.works.tosplit.transaction.data.TransactionRepository
import biped.works.tosplit.transaction.data.domain.TimeSpan
import biped.works.tosplit.transaction.data.domain.Transaction
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(transactionLocator: TransactionLocator): Result<Transaction> {
        val dueDate = transactionLocator.due
        val metaId = transactionLocator.metaId

        val timeSpan = TimeSpan(dueDate, dueDate)
        val transaction = transactionRepository
            .getTransactionMetadata(metaId)
            .createTransactions(timeSpan)
            .firstOrNull()

        return if (transaction == null) Result.failure(Exception("No results found for metaId"))
        else Result.success(transaction)
    }
}
