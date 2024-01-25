package biped.works.tosplit.transaction

import biped.works.tosplit.transaction.data.TransactionRepository
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(transaction: Transaction) {
//        val transactionMetadata = transaction.toMetadata()
//        transactionRepository.saveMetadata(transactionMetadata)
    }
}