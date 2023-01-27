package biped.works.tosplit.transaction.di

import biped.works.tosplit.transaction.CreateOperationMetadataUseCase
import biped.works.tosplit.transaction.data.TransactionMetadataRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TransactionModule {

    @Bean
    fun provideCreateOperationMetadataUseCase(
        transactionRepository: TransactionMetadataRepository
    ) = CreateOperationMetadataUseCase(transactionRepository)


    @Bean
    fun provideTransactionRepository() = TransactionMetadataRepository()
}