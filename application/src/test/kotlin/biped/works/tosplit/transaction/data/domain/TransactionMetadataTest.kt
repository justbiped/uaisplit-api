package biped.works.tosplit.transaction.data.domain

import biped.works.tosplit.transactionMetadata
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDate


class TransactionMetadataTest {

    private val date20240110 = LocalDate.parse("2024-01-10")
    private val date20240210 = LocalDate.parse("2024-02-10")
    private val date20240310 = LocalDate.parse("2024-03-10")

    @Test
    fun `create transactions given a transaction metadata`() {
        val timeSpan = TimeSpan()
        val recurrence = mockk<Recurrence>()
        val metadata = transactionMetadata(name = "Car Rent", recurrence = recurrence)
        every { recurrence.generateDueDates(timeSpan) } returns listOf(date20240110, date20240210, date20240310)

        val transactions = metadata.createTransactions()

        assertThat(transactions).hasSize(3)
    }
}