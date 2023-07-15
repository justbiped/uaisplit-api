package biped.works.tosplit

import biped.works.tosplit.transaction.data.Operation
import biped.works.tosplit.transaction.data.OperationMetadata
import biped.works.tosplit.transaction.data.Recurrence
import java.math.BigDecimal
import java.time.LocalDate

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