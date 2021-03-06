package com.karbonpowered.server

import io.ktor.utils.io.*
import io.ktor.utils.io.core.*
import kotlin.experimental.and
import kotlin.experimental.or

fun Input.readVarInt(): Int = readVarInt { readByte() }

inline fun readVarInt(byte: () -> Byte): Int {
    var numRead = 0
    var result = 0
    var read: Byte
    do {
        read = byte()
        val value = (read and 127).toInt()
        result = result or (value shl 7 * numRead)
        numRead++
        if (numRead > 5) {
            throw RuntimeException("VarInt is too big")
        }
    } while (read and 128.toByte() != 0.toByte())
    return result
}

inline fun writeVarInt(i: Int, writeByte: (Byte) -> Unit) {
    var value = i
    do {
        var temp = (value and 127).toByte()
        value = value ushr 7
        if (value != 0) {
            temp = temp or 128.toByte()
        }
        writeByte(temp)
    } while (value != 0)
}

fun Output.writeVarInt(i: Int) = writeVarInt(i) { writeByte(it) }

suspend fun ByteReadChannel.readVarInt(): Int {
    var numRead = 0
    var result = 0
    var read: Byte
    do {
        read = readByte()
        val value = (read and 127).toInt()
        result = result or (value shl 7 * numRead)
        numRead++
        if (numRead > 5) {
            throw RuntimeException("VarInt is too big")
        }
    } while (read and 128.toByte() != 0.toByte())
    return result
}

suspend fun ByteWriteChannel.writeVarInt(i: Int) {
    var value = i
    do {
        var temp = (value and 127).toByte()
        value = value ushr 7
        if (value != 0) {
            temp = temp or 128.toByte()
        }
        writeByte(temp)
    } while (value != 0)
}

fun Output.writeBoolean(boolean: Boolean) = writeByte(if (boolean) 1.toByte() else 0.toByte())
fun Input.readBoolean(): Boolean = readByte() == 1.toByte()

private const val DEFAULT_MAX_STRING_SIZE = 65536 // 64KiB

fun Input.readString(capacity: Int = DEFAULT_MAX_STRING_SIZE): String {
    val length = readVarInt()
    return readString(capacity, length)
}

private fun Input.readString(capacity: Int, length: Int): String {
    check(length >= 0) { "Hot a negative-length string ($length)" }
    check(length <= capacity * 4) { "Bad string size (got $length, maximum is $capacity)" }
//    check(availableForRead >= length) {
//        "Trying to read a string that is too long (wanted $length, only have $availableForRead)"
//    }
    val string = String(readBytes(length))
    check(string.length <= capacity) { "Got a too-long string (got ${string.length}, max $capacity)" }
    return string
}

fun Output.writeString(string: String) {
    val bytes = string.toByteArray()
    writeVarInt(bytes.size)
    writeFully(bytes)
}

suspend fun ByteReadChannel.readUShort(): UShort = readShort().toUShort()