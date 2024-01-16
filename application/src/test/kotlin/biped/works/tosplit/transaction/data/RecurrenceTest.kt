package biped.works.tosplit.transaction.data

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RecurrenceTest {

    @Test
    fun `parse month recurrence to string for given params`() {
        val recurrence = Recurrence(
            frequency = Frequency.MONTH,
            interval = 9,
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
                interval = 9,
                times = 2,
                workday = false
            )
        )
    }
}