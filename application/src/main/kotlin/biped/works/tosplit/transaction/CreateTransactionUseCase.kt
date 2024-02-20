package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.TransactionRepository
import biped.works.tosplit.transaction.data.domain.Transaction
import biped.works.tosplit.transaction.data.domain.TransactionMetadata
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(transaction: Transaction) {
        try {
            val metadata = TransactionMetadata.fromTransaction(transaction)
            transactionRepository.saveTransactionMetadata(metadata)
        } catch (error: Throwable) {
            error.printStackTrace()
        }
    }
}