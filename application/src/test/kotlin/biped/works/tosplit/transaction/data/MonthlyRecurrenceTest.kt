package biped.works.tosplit.transaction.data

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate


class MonthlyRecurrenceTest {

    @Test
    fun `conclusion is set to max value when frequency times is less than 1`() {
        val recurrence = MonthlyRecurrence(
            recurrence(start = LocalDate.parse("2024-01-10"), frequency = "times=-1")
        )

        val conclusion = recurrence.conclusion

        assertThat(conclusion == LocalDate.MAX)
    }

    @Test
    fun `conclusion is equals the start date when frequency times is 1`() {
        val start = LocalDate.parse("2024-01-10")
        val recurrence = MonthlyRecurrence(recurrence(start = start, frequency = "times=1"))

        val conclusion = recurrence.conclusion

        assertThat(conclusion).isEqualTo(start)
    }

    @Test
    fun `returns 01-10 date for one time recurrence matching time span 01-10 to infinity`() {
        val recurrence = recurrence(
            start = LocalDate.parse("2024-01-10"),
            frequency = "times=1"
        )
        val monthlyRecurrence = MonthlyRecurrence(recurrence)

        val dueDates = monthlyRecurrence.generateDueDates(TimeSpan(start = LocalDate.parse("2024-01-01")))

        assertThat(dueDates).hasSize(1)
        assertThat(dueDates.first()).isEqualTo(
            LocalDate.parse("2024-01-10")
        )
    }

    @Test
    fun `generates due dates 01-10 ,02-10, 03-10 for 3 a months recurrence with time span 01-10 to infinity`() {
        val timeSpan = TimeSpan(start = LocalDate.parse("2024-01-10"))
        val recurrence = recurrence(
            start = LocalDate.parse("2024-01-10"),
            frequency = "times=3"
        )
        val monthlyRecurrence = MonthlyRecurrence(recurrence)

        val dueDates = monthlyRecurrence.generateDueDates(timeSpan)

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
            frequency = "times=3"
        )
        val monthlyRecurrence = MonthlyRecurrence(recurrence)

        val dueDates = monthlyRecurrence.generateDueDates(timeSpan)

        assertThat(dueDates).hasSize(2)
        assertThat(dueDates).containsExactly(
            LocalDate.parse("2024-02-10"),
            LocalDate.parse("2024-03-10"),
        )
    }

    @Test
    fun `generates due dates 01-10, 02-10 for 3 a months recurrence with time span 01-10 to 02-10`() {
        val timeSpan = TimeSpan(start = LocalDate.parse("2024-01-10"), LocalDate.parse("2024-02-10"))
        val recurrence = recurrence(
            start = LocalDate.parse("2024-01-10"),
            frequency = "times=3"
        )
        val monthlyRecurrence = MonthlyRecurrence(recurrence)

        val dueDates = monthlyRecurrence.generateDueDates(timeSpan)

        assertThat(dueDates).hasSize(2)
        assertThat(dueDates).containsExactly(
            LocalDate.parse("2024-01-10"),
            LocalDate.parse("2024-02-10"),
        )
    }
}