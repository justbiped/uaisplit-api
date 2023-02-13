package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.Transaction
import biped.works.tosplit.transaction.data.TransactionMetadataRepository
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionMetadataRepository
) {
    operator fun invoke(transaction: Transaction) {
        val transactionMetadata = transaction.toMetadata()
        transactionRepository.saveMetadata(transactionMetadata)
    }
}