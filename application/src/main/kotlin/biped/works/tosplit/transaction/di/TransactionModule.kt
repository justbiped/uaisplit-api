package biped.works.tosplit.transaction.di

import biped.works.tosplit.transaction.CreateTransactionUseCase
import biped.works.tosplit.transaction.ListOperationsUseCase
import biped.works.tosplit.transaction.data.OperationMetadataRepository
import com.google.cloud.firestore.Firestore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order

@Order(200)
@Configuration
class TransactionModule {

    @Bean
    fun provideCreateOperationMetadataUseCase(
        transactionRepository: OperationMetadataRepository
    ) = CreateTransactionUseCase(transactionRepository)

    @Bean
    fun provideListOperationUseCase(
        transactionRepository: OperationMetadataRepository
    ) = ListOperationsUseCase(transactionRepository)

    @Bean
    fun provideTransactionRepository(firestore: Firestore) = OperationMetadataRepository(firestore)
}