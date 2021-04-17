package com.karbonpowered.protocol

import com.karbonpowered.api.audience.MessageTypes
import com.karbonpowered.api.entity.living.player.GameModes
import kotlin.reflect.KClass

object MagicValues {
    val VALUES = mutableMapOf<Any, MutableList<Any>>()

    init {
        register(GameModes.SURVIVAL, 0)
        register(GameModes.CREATIVE, 1)
        register(GameModes.ADVENTURE, 2)
        register(GameModes.SPECTATOR, 3)

        register(MessageTypes.ACTION_BAR, 0)
        register(MessageTypes.CHAT, 1)
        register(MessageTypes.SYSTEM, 2)
    }

    private fun register(key: Any, value: Any) {
        VALUES.getOrPut(key) { ArrayList() }.add(value)
    }

    fun <T : Any> key(keyType: KClass<T>, value: Any): T {
        for (key in VALUES.keys) {
            if (keyType == key::class) {
                for (v in VALUES[key] ?: throw UnmappedValueException(value, keyType)) {
                    if (v == value) {
                        return key as T
                    }
                }
            }
        }
        throw UnmappedValueException(value, keyType)
    }

    inline fun <reified T : Any> key(value: Any): T = key(T::class, value)

    fun <T : Any> value(valueType: KClass<T>, key: Any): T {
        if (VALUES.containsKey(key)) {
            for (value in VALUES[key] ?: throw UnmappedKeyException(key, valueType)) {
                if (valueType == value::class) {
                    return value as T
                }
            }
        }
        throw UnmappedKeyException(key, valueType)
    }

    inline fun <reified T : Any> value(key: Any): T = value(T::class, key)

    data class UnmappedKeyException(
        val key: Any,
        val keyType: KClass<*>
    ) : IllegalArgumentException("Key $key has no mapping for key class $keyType")

    data class UnmappedValueException(
        val value: Any,
        val keyType: KClass<*>
    ) : IllegalArgumentException("Value $value has no mapping for key class $keyType")
}