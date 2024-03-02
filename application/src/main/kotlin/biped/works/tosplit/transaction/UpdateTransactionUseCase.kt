package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.TransactionRepository
import biped.works.tosplit.transaction.data.domain.Transaction
import javax.inject.Inject

class
UpdateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    operator fun invoke(transaction: Transaction, policy: UpdatePolicy) {
        when (policy) {
            UpdatePolicy.CURRENT -> print("change only the current transaction")
            UpdatePolicy.UPCOMING -> print("change just the upcoming transactions")
            // todo: The upcoming option should be offered just for recurrent transactions
        }
        //transactionRepository.saveTransactionMetadata()
    }
}

enum class UpdatePolicy {
    CURRENT,
    UPCOMING
}
