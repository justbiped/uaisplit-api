package biped.works.tosplit.operation.data

import biped.works.tosplit.transaction.data.Frequency
import biped.works.tosplit.transaction.data.Recurrence
import biped.works.tosplit.transaction.data.RecurrenceRequest
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RecurrenceTest {

    @Test
    fun `parse month recurrence to string for given params`() {
        val recurrence = Recurrence(
            frequency = Frequency.MONTH,
            day = 9,
            times = 2,
            workday = false
        )

        val recurrenceMetadata = recurrence.toString()

        assertEquals(recurrenceMetadata, "frequency=MONTH;day=9;times=2;workday=false")
    }

    @Test
    fun `parse month recurrence from string`() {
        val recurrence = Recurrence.parse("frequency=MONTH;day=9;times=2;workday=false")

        assertThat(recurrence).isEqualTo(
            Recurrence(
                frequency = Frequency.MONTH,
                day = 9,
                times = 2,
                workday = false
            )
        )
    }
}