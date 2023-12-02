package biped.works.tosplit.transaction.data

import biped.works.tosplit.metadataFixture
import com.google.common.truth.Truth.assertThat
import java.time.LocalDate
import org.junit.jupiter.api.Test

class TransactionMetadataTest {

    @Test
    fun `create month operation for a one time recurrence`() {
        val recurrence = Recurrence(frequency = Frequency.MONTH, times = 1)
        val entry = LocalDate.of(2023, 1, 10)

        val operations = metadataFixture(entry = entry, conclusion = entry, recurrence = recurrence).createTransactions()

        assertThat(operations).hasSize(1)
        assertThat(operations.first().due).isEqualTo(entry)
    }

    @Test
    fun `create 5 month transaction for a 5 times recurrence`() {
        val recurrence = Recurrence(frequency = Frequency.MONTH, times = 5)
        val range = Range(
           entry =  LocalDate.of(2023, 1, 10),
            conclusion = LocalDate.of(2023, 5, 10),
        )

        val operations = metadataFixture(
            entry = range.entry,
            conclusion = range.conclusion,
            recurrence = recurrence
        ).createTransactions(range)

        assertThat(operations).hasSize(5)
        assertThat(operations[0].due).isEqualTo(range.entry)
        assertThat(operations[1].due).isEqualTo(range.entry.plusMonths(1))
        assertThat(operations[2].due).isEqualTo(range.entry.plusMonths(2))
        assertThat(operations[3].due).isEqualTo(range.entry.plusMonths(3))
        assertThat(operations[4].due).isEqualTo(range.entry.plusMonths(4))
    }

    @Test
    fun `respect month metadata conclusion when the range is greater`() {
        val recurrence = Recurrence(frequency = Frequency.MONTH, times = 5)
        val entry = LocalDate.of(2023, 1, 10)
        val conclusion = LocalDate.of(2023, 2, 10)

        val operations = metadataFixture(
            entry = entry,
            conclusion = conclusion,
            recurrence = recurrence
        ).createTransactions()

        assertThat(operations).hasSize(2)
        assertThat(operations[0].due).isEqualTo(entry)
        assertThat(operations[1].due).isEqualTo(entry.plusMonths(1))
    }

    @Test
    fun `create operations for weekly custom operation`() {
        val entry = LocalDate.of(2023, 1, 1)
        val conclusion = LocalDate.of(2023, 1, 29)
        val recurrence = Recurrence(frequency = Frequency.CUSTOM, interval = 7)

        val operations =
            metadataFixture(entry = entry, recurrence = recurrence).createTransactions(Range(entry, conclusion))

        assertThat(operations).hasSize(5)
        assertThat(operations[0].due).isEqualTo(LocalDate.of(2023, 1, 1))
        assertThat(operations[1].due).isEqualTo(LocalDate.of(2023, 1, 8))
        assertThat(operations[2].due).isEqualTo(LocalDate.of(2023, 1, 15))
        assertThat(operations[3].due).isEqualTo(LocalDate.of(2023, 1, 22))
        assertThat(operations[4].due).isEqualTo(LocalDate.of(2023, 1, 29))
    }
}