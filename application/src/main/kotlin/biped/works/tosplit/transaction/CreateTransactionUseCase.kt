package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.Transaction
import biped.works.tosplit.transaction.data.OperationRepository
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val operationRepository: OperationRepository
) {
    operator fun invoke(transaction: Transaction) {
        val transactionMetadata = transaction.toMetadata()
        operationRepository.saveMetadata(transactionMetadata)
    }
}