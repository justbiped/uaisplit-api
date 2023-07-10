package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.Transaction
import biped.works.tosplit.transaction.data.OperationMetadataRepository
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val operationMetadataRepository: OperationMetadataRepository
) {
    operator fun invoke(transaction: Transaction) {
        val transactionMetadata = transaction.toMetadata()
        operationMetadataRepository.saveMetadata(transactionMetadata)
    }
}