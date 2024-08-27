package com.mooncloak.kodetools.aes

internal fun keyExpansion(key: ByteArray, numberOfWords: Int, numberOfRounds: Int): Array<ByteArray> {
    val w = Array(4 * (numberOfRounds + 1)) { ByteArray(4) } // Expanded key

    var i = 0
    while (i <= numberOfWords - 1) {
        w[i] = key.copyOfRange(fromIndex = 4 * i, toIndex = 4 * i + 4)
        i++
    }

    var temp: ByteArray
    while (i <= 4 * numberOfRounds + 3) {
        temp = w[i - 1]

        if (i % numberOfWords == 0) {
            temp = subWord(rotWord(temp))
            temp[0] = (temp[0].toInt() xor rcon[i / numberOfWords].toInt()).toByte()
        } else if (numberOfWords > 6 && i % numberOfWords == 4) {
            temp = subWord(temp)
        }

        for (j in 0..3) {
            w[i][j] = (w[i - numberOfWords][j].toInt() xor temp[j].toInt()).toByte()
        }

        i++
    }

    return w
}

internal fun rotWord(word: ByteArray): ByteArray =
    byteArrayOf(word[1], word[2], word[3], word[0])

@OptIn(ExperimentalUnsignedTypes::class)
internal fun subWord(word: ByteArray): ByteArray {
    val result = ByteArray(4)

    for (i in 0..3) {
        val x = word[i].toInt() and 0x0F
        val y = word[i].toInt() shr 4

        result[i] = sbox[y][x].toByte()
    }

    return result
}

private val rcon = byteArrayOf(
    0x01,
    0x02,
    0x04,
    0x08,
    0x10,
    0x20,
    0x40,
    0x80.toByte(),
    0x1b,
    0x36,
    0x6c,
    0xd8.toByte(),
    0xab.toByte(),
    0x4d,
    0x9a.toByte(),
    0x2f,
    0x5e,
    0xbc.toByte(),
    0x63,
    0xc6.toByte(),
    0x97.toByte(),
    0x35,
    0x6a,
    0xd4.toByte(),
    0xb3.toByte(),
    0x7d,
    0xfa.toByte(),
    0xef.toByte(),
    0xc5.toByte(),
    0x91.toByte(),
    0x39
)
