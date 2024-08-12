package biped.works.tosplit.transaction.data.domain

import biped.works.tosplit.transactionMetadata
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.reflect.KClass

class TransactionMetadataTest {
    private val date20240110 = LocalDate.parse("2024-01-10")
    private val date20240210 = LocalDate.parse("2024-02-10")
    private val date20240310 = LocalDate.parse("2024-03-10")

    @Test
    fun `create transactions given a transaction metadata`() {
        val monthlyRecurrence = MonthlyRecurrence(recurrence(date20240110, "times=3"))
        val metadata = transactionMetadata(name = "Car Rent", recurrence = monthlyRecurrence)

        val transactions = metadata.createTransactions()

        assertThat(transactions).hasSize(3)
        assertThat(transactions.map { it.due }).containsExactly(date20240110, date20240210, date20240310)
    }
}