package biped.works.tosplit.transaction.data

import java.math.BigDecimal
import java.time.LocalDate
import org.junit.jupiter.api.Test

class OperationMetadataTest {

    @Test
    fun `create month operations for the given metadata`() {
        metadataFixture()
        operationFixture()
    }
}


fun metadataFixture(
    id: String = "",
    name: String = "Car rent",
    description: String = "Month car rent",
    entry: LocalDate = LocalDate.now(),
    conclusion: LocalDate = LocalDate.now(),
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