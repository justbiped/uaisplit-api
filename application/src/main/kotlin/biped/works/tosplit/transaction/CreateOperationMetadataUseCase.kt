package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.Transaction
import biped.works.tosplit.transaction.data.TransactionMetadataRepository
import javax.inject.Inject

class CreateOperationMetadataUseCase @Inject constructor(
    operationRepository: TransactionMetadataRepository
) {
    operator fun invoke(transaction: Transaction) {
        //operationRepository.saveMetadata(transaction.generateMetadata())
        transaction.generateMetadata()
    }
}