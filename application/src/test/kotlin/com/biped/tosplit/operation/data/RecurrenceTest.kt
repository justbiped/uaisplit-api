package com.biped.tosplit.operation.data

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RecurrenceTest {

    @Test
    fun `parse mont recurrence for given params`() {
        val recurrence = Recurrence(
            frequency = Frequency.MONTH,
            day = 9,
            count = 2,
            workDay = false
        )

        val recurrenceMetadata = recurrence.toString()

        assertEquals(recurrenceMetadata, "frequency=MONTH;day=9;count=2;workday=false")
    }
}