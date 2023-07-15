package biped.works.tosplit.transaction.data

import biped.works.tosplit.metadataFixture
import com.google.common.truth.Truth.assertThat
import java.time.LocalDate
import org.junit.jupiter.api.Test

class OperationMetadataTest {

    @Test
    fun `create month operations for the given metadata`() {
        val entry = LocalDate.of(2023, 1, 10)
        val recurrence = Recurrence(frequency = Frequency.MONTH, times = 6)

        val operations = metadataFixture(entry = entry, recurrence = recurrence).createOperations()

        assertThat(operations).hasSize(1)
        assertThat(operations.first().duty).isEqualTo(entry)
    }

    @Test
    fun `create operations for weekly custom operation`() {
        val entry = LocalDate.of(2023, 1, 1)
        val recurrence = Recurrence(frequency = Frequency.CUSTOM, interval = 7)

        val operations = metadataFixture(entry = entry, recurrence = recurrence).createOperations()

        assertThat(operations).hasSize(5)
        assertThat(operations[0].duty).isEqualTo(LocalDate.of(2023, 1, 1))
        assertThat(operations[1].duty).isEqualTo(LocalDate.of(2023, 1, 8))
        assertThat(operations[2].duty).isEqualTo(LocalDate.of(2023, 1, 15))
        assertThat(operations[3].duty).isEqualTo(LocalDate.of(2023, 1, 22))
        assertThat(operations[4].duty).isEqualTo(LocalDate.of(2023, 1, 29))
    }
}