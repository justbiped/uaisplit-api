package biped.works.tosplit

import io.mockk.mockk
import kotlin.reflect.KClass

inline fun <reified T : Any> relaxedMock(
    name: String? = "",
    vararg moreInterfaces: KClass<*>,
    block: T.() -> Unit = {}
) = mockk<T>(name = name, moreInterfaces = moreInterfaces, block = block, relaxed = true)

inline fun <reified T : Any> mock(
    name: String? = "",
    vararg moreInterfaces: KClass<*>,
    block: T.() -> Unit = {}
) = mockk<T>(name = name, relaxUnitFun = true, moreInterfaces = moreInterfaces, block = block)