package biped.works.tosplit.transaction.di

import biped.works.tosplit.transaction.CreateTransactionUseCase
import biped.works.tosplit.transaction.data.TransactionMetadataRepository
import com.google.cloud.firestore.Firestore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order

@Order(200)
@Configuration
class TransactionModule {

    @Bean
    fun provideCreateOperationMetadataUseCase(
        transactionRepository: TransactionMetadataRepository
    ) = CreateTransactionUseCase(transactionRepository)


    @Bean
    fun provideTransactionRepository(firestore: Firestore) = TransactionMetadataRepository(firestore)
}