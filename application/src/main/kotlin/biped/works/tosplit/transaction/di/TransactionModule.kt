package biped.works.tosplit.transaction.di

import biped.works.tosplit.transaction.CreateTransactionUseCase
import biped.works.tosplit.transaction.ListTransactionsUseCase
import biped.works.tosplit.transaction.UpdateTransactionUseCase
import biped.works.tosplit.transaction.data.TransactionRepository
import com.google.cloud.firestore.Firestore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order

@Order(200)
@Configuration
class TransactionModule {

    @Bean
    fun provideCreateTransactionMetadataUseCase(
        transactionRepository: TransactionRepository
    ) = CreateTransactionUseCase(transactionRepository)

    @Bean
    fun provideListTransactionUseCase(
        transactionRepository: TransactionRepository
    ) = ListTransactionsUseCase(transactionRepository)

    @Bean
    fun provideUpdateTransactionUseCase(
        transactionRepository: TransactionRepository
    ) = UpdateTransactionUseCase(transactionRepository)

    @Bean
    fun provideTransactionRepository(firestore: Firestore) = TransactionRepository(firestore)
}