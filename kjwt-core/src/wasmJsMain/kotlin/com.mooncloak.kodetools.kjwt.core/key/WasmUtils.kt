package com.mooncloak.kodetools.kjwt.core.key

internal fun createJsObject(): JsAny = js("{}")

internal fun <T : JsAny> jsArrayOf(vararg values: T): JsArray<T> {
    val array = createJsArrayAsAny().unsafeCast<JsArray<T>>()

    values.forEachIndexed { index, value ->
        array[index] = value
    }

    return array
}

private fun createJsArrayAsAny(): JsAny = js("Array()")
