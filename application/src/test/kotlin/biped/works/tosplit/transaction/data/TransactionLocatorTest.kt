package biped.works.tosplit.transaction.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.format.DateTimeParseException


class TransactionLocatorTest {

    @Test
    fun `creating locator by meta id and due date generates due date concat with meta id key`() {
        val metaId = "metaIdKey123"
        val due = LocalDate.parse("2024-01-01")

        val locator = TransactionLocator(metaId, due)

        assertThat(locator.key).isEqualTo("20240101metaIdKey123")
    }

    @Test
    fun `creating locator by key requires due date concat with meta id pattern _ yyyyMMdd{Meta Id}`() {
        val locator = TransactionLocator("20240101metaIdKey123")

        assertThat(locator.due).isEqualTo(LocalDate.parse("2024-01-01"))
        assertThat(locator.metaId).isEqualTo("metaIdKey123")
    }

    @Test
    fun `throws illegal argument exception if meta id is not concat with due date yyyyMMdd{MetaId}`() {
        val dueDate = "20240101"
        val metaId = ""

        assertThrows<IllegalArgumentException> { TransactionLocator("$dueDate$metaId") }
    }

    @Test
    fun `throws date time parse exception if the due data does not match yyyyMMdd`() {
        val dueDate = "01012024"
        val metaId = "metaIdKey123"

        assertThrows<DateTimeParseException> { TransactionLocator("${dueDate}$metaId") }
    }
}