package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.TransactionRepository
import biped.works.tosplit.transaction.data.domain.Transaction
import biped.works.tosplit.transaction.data.domain.TransactionMetadata
import javax.inject.Inject

class
UpdateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    operator fun invoke(transaction: Transaction, policy: UpdatePolicy): Result<Unit> {
        val metadata = TransactionMetadata.fromTransaction(transaction)
        return try {
            when (policy) {
                UpdatePolicy.CURRENT -> transactionRepository.saveTransactionMetadata(metadata)
//            UpdatePolicy.UPCOMING -> print("change just the upcoming transactions")
//            UpdatePolicy.ALL -> print("")
                else -> throw Exception("Update policy not supported")
            }
            Result.success(Unit)
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}

enum class UpdatePolicy {
    CURRENT,
    UPCOMING,
    ALL
}