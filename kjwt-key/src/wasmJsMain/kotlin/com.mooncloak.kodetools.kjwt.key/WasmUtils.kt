package com.mooncloak.kodetools.kjwt.key

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Int8Array
import org.khronos.webgl.get

internal fun ArrayBuffer.toByteArray(): ByteArray {
    val array = Int8Array(this)
    val result = ByteArray(array.length)

    for (i in 0 until array.length) {
        result[i] = array[i]
    }

    return result
}
