package com.karbonpowered.engine.component

import kotlin.reflect.KClass

interface ComponentHolder : Iterable<Component> {
    operator fun <T : Component> get(type: KClass<T>, factory: () -> T): T = add(type, factory())
    operator fun <T : Component> get(type: KClass<T>): T

    fun <T : Component> add(component: T, attach: Boolean = true): T = add(component::class, component, attach)

    fun <T : Component> add(key: KClass<out T>, component: T, attach: Boolean = true): T
}



