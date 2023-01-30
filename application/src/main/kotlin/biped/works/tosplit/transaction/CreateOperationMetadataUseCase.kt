package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.Transaction
import biped.works.tosplit.transaction.data.TransactionMetadataRepository
import javax.inject.Inject

class CreateOperationMetadataUseCase @Inject constructor(
    private val transactionRepository: TransactionMetadataRepository
) {
    operator fun invoke(transaction: Transaction) {
        transactionRepository.saveMetadata(transaction.generateMetadata())
        transaction.generateMetadata()
    }
}