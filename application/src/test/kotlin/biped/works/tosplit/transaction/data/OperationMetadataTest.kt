package biped.works.tosplit.transaction.data

import biped.works.tosplit.metadataFixture
import com.google.common.truth.Truth.assertThat
import java.time.LocalDate
import org.junit.jupiter.api.Test

class OperationMetadataTest {

    @Test
    fun `create month operation for a one time recurrence`() {
        val recurrence = Recurrence(frequency = Frequency.MONTH, times = 1)
        val entry = LocalDate.of(2023, 1, 10)

        val operations = metadataFixture(entry = entry, conclusion = entry, recurrence = recurrence).createOperations()

        assertThat(operations).hasSize(1)
        assertThat(operations.first().duty).isEqualTo(entry)
    }

    @Test
    fun `create 5 month operation for a 5 times recurrence`() {
        val recurrence = Recurrence(frequency = Frequency.MONTH, times = 5)
        val range = Range(
            LocalDate.of(2023, 1, 10),
            LocalDate.of(2023, 5, 10),
        )

        val operations = metadataFixture(
            entry = range.entry,
            conclusion = range.conclusion,
            recurrence = recurrence
        ).createOperations(range)

        assertThat(operations).hasSize(5)
        assertThat(operations[0].duty).isEqualTo(range.entry)
        assertThat(operations[1].duty).isEqualTo(range.entry.plusMonths(1))
        assertThat(operations[2].duty).isEqualTo(range.entry.plusMonths(2))
        assertThat(operations[3].duty).isEqualTo(range.entry.plusMonths(3))
        assertThat(operations[4].duty).isEqualTo(range.entry.plusMonths(4))
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
        ).createOperations()

        assertThat(operations).hasSize(2)
        assertThat(operations[0].duty).isEqualTo(entry)
        assertThat(operations[1].duty).isEqualTo(entry.plusMonths(1))
    }

    @Test
    fun `create operations for weekly custom operation`() {
        val entry = LocalDate.of(2023, 1, 1)
        val conclusion = LocalDate.of(2023, 1, 30)
        val recurrence = Recurrence(frequency = Frequency.CUSTOM, interval = 7)

        val operations =
            metadataFixture(entry = entry, recurrence = recurrence).createOperations(Range(entry, conclusion))

        assertThat(operations).hasSize(5)
        assertThat(operations[0].duty).isEqualTo(LocalDate.of(2023, 1, 1))
        assertThat(operations[1].duty).isEqualTo(LocalDate.of(2023, 1, 8))
        assertThat(operations[2].duty).isEqualTo(LocalDate.of(2023, 1, 15))
        assertThat(operations[3].duty).isEqualTo(LocalDate.of(2023, 1, 22))
        assertThat(operations[4].duty).isEqualTo(LocalDate.of(2023, 1, 29))
    }
}