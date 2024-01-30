package biped.works.tosplit

import biped.works.tosplit.transaction.data.Recurrence
import biped.works.tosplit.transaction.data.TransactionMetadata
import biped.works.tosplit.transaction.data.recurrence
import biped.works.tosplit.transaction.data.transaction
import org.apache.tomcat.jni.Local
import java.math.BigDecimal
import java.time.LocalDate

fun metadataFixture(
    id: String = "",
    name: String = "Car rent",
    description: String = "Month car rent",
    value: BigDecimal = BigDecimal.valueOf(222.00),
    recurrence: Recurrence = recurrence(LocalDate.now(), LocalDate.now(), "")
) = TransactionMetadata(
    id = id,
    name = name,
    description = description,
    value = value,
    recurrence = recurrence,
)

fun transactionFixture(
    id: String = "",
    metaId: String = "",
    name: String = "Car rent",
    description: String = "Month car rent",
    duty: LocalDate = LocalDate.now(),
    value: BigDecimal = BigDecimal.valueOf(222.00)
) = transaction(
    id = id,
    metaId = metaId,
    name = name,
    description = description,
    due = duty,
    value = value
)