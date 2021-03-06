package com.karbonpowered.engine.event

import com.karbonpowered.engine.entity.KarbonEntity
import com.karbonpowered.engine.world.reference.ChunkReference

data class EntityStopObservingChunksEvent(
    val observer: KarbonEntity,
    val observed: Set<ChunkReference>
) : EngineEvent {
    override fun call(listener: EngineEventListener) {
        listener.onEntityStopObservingChunks(this)
    }
}