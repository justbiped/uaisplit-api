package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.Operation
import biped.works.tosplit.transaction.data.OperationRepository
import biped.works.tosplit.transaction.data.Range
import java.time.LocalDate
import javax.inject.Inject

class ListOperationsUseCase @Inject constructor(
    private val operationRepository: OperationRepository
) {

    operator fun invoke(entry: LocalDate, conclusion: LocalDate): List<Operation> {
        val range = Range(entry, conclusion)
        return operationRepository
            .getOperationMetadataList(range)
            .flatMap { it.createOperations(range) }
    }
}