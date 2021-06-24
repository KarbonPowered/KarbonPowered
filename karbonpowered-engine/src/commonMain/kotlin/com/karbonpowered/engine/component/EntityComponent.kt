package com.karbonpowered.engine.component

import com.karbonpowered.engine.entity.KarbonEntity

abstract class EntityComponent(
    val entity: KarbonEntity
) : Component() {

    fun onSpawned() {
    }

}