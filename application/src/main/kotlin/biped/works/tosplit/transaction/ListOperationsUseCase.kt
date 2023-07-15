package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.Operation
import biped.works.tosplit.transaction.data.OperationRepository
import javax.inject.Inject

class ListOperationsUseCase @Inject constructor(
    private val operationRepository: OperationRepository
) {

    operator fun invoke(): List<Operation> {
        return operationRepository.getOperationMetadataList().flatMap { it.createOperations() }
    }
}