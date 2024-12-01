package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.TransactionLocator
import biped.works.tosplit.transaction.data.TransactionRepository
import biped.works.tosplit.transaction.data.domain.Transaction
import biped.works.tosplit.transaction.data.domain.TransactionMetadata
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(transaction: Transaction): Transaction {
        try {
            val metadata = TransactionMetadata.fromTransaction(transaction)
            val metaId = transactionRepository.saveTransactionMetadata(metadata)
            val transactionLocator = TransactionLocator(metaId, transaction.due)
            return transaction.copy(id = transactionLocator.key)
        } catch (error: Throwable) {
            error.printStackTrace()
            throw Exception("Blabla")
        }
    }
}