package biped.works.tosplit.transaction.data

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate


class MonthlyRecurrenceTest {

    @Test
    fun `returns 01-10 date for one time recurrence matching time span 01-10 to infinity`() {
        val timeSpan = TimeSpan(start = LocalDate.parse("2024-01-10"))
        val recurrence = recurrence(
            start = LocalDate.parse("2024-01-10"),
            conclusion = LocalDate.parse("2024-01-10"),
            frequency = ""
        )
        val monthlyRecurrence = MonthlyRecurrence(recurrence)

        val dueDates = monthlyRecurrence.generateDupDates(TimeSpan(start = LocalDate.parse("2024-01-01")))

        assertThat(dueDates).hasSize(1)
        assertThat(dueDates.first()).isEqualTo(timeSpan)
    }

    @Test
    fun `generates due dates 01-10 ,02-10, 03-10 for 3 a months recurrence with time span 01-10 to infinity`() {
        val timeSpan = TimeSpan(start = LocalDate.parse("2024-01-10"))
        val recurrence = recurrence(
            start = LocalDate.parse("2024-01-10"),
            conclusion = LocalDate.parse("2024-03-10"),
            frequency = ""
        )
        val monthlyRecurrence = MonthlyRecurrence(recurrence)

        val dueDates = monthlyRecurrence.generateDupDates(timeSpan)

        assertThat(dueDates).hasSize(3)
        assertThat(dueDates).containsExactly(
            LocalDate.parse("2024-01-10"),
            LocalDate.parse("2024-02-10"),
            LocalDate.parse("2024-03-10"),
        )
    }

    @Test
    fun `generates due dates 02-10, 03-10 for 3 a months recurrence with time span 01-11 to infinity`() {
        val timeSpan = TimeSpan(start = LocalDate.parse("2024-01-11"))
        val recurrence = recurrence(
            start = LocalDate.parse("2024-01-10"),
            conclusion = LocalDate.parse("2024-03-10"),
            frequency = ""
        )
        val monthlyRecurrence = MonthlyRecurrence(recurrence)

        val dueDates = monthlyRecurrence.generateDupDates(timeSpan)

        assertThat(dueDates).hasSize(2)
        assertThat(dueDates).containsExactly(
            LocalDate.parse("2024-02-10"),
            LocalDate.parse("2024-03-10"),
        )
    }
}