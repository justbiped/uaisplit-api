package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.OperationMetadata
import biped.works.tosplit.transaction.data.OperationMetadataRepository
import javax.inject.Inject

class ListOperationsUseCase @Inject constructor(
    private val operationMetadataRepository: OperationMetadataRepository
) {

    operator fun invoke(): List<OperationMetadata.Operation> {
        return operationMetadataRepository.getOperationMetadataList().flatMap { it.createOperations() }
    }
}