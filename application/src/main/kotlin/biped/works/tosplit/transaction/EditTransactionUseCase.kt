package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.domain.Transaction

class
EditTransactionUseCase {

    operator fun invoke(transaction: Transaction, policy: UpdatePolicy){

    }
}

enum class UpdatePolicy {
    ALL,
    CURRENT,
    ALL_AHEAD
}
