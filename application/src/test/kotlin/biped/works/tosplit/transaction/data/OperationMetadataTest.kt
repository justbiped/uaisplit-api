package biped.works.tosplit.transaction.data

import com.google.common.truth.Truth.assertThat
import java.math.BigDecimal
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
}


fun metadataFixture(
    id: String = "",
    name: String = "Car rent",
    description: String = "Month car rent",
    entry: LocalDate = LocalDate.of(2023, 1, 10),
    conclusion: LocalDate = LocalDate.of(2023, 6, 10),
    value: BigDecimal = BigDecimal.valueOf(222.00),
    recurrence: Recurrence = Recurrence()
) = OperationMetadata(
    id = id,
    name = name,
    description = description,
    entry = entry,
    conclusion = conclusion,
    value = value,
    recurrence = recurrence,
)

fun operationFixture(
    id: String = "",
    metaId: String = "",
    name: String = "Car rent",
    description: String = "Month car rent",
    duty: LocalDate = LocalDate.now(),
    value: BigDecimal = BigDecimal.valueOf(222.00)
) = Operation(
    id = id,
    metaId = metaId,
    name = name,
    description = description,
    duty = duty,
    value = value
)