package com.karbonpowered.engine.world.generator

import com.karbonpowered.engine.util.cuboid.CuboidIntBuffer
import com.karbonpowered.engine.world.KarbonWorld

class FlatWorldGenerator(
    val material: Int = 1
) : WorldGenerator {
    override suspend fun generate(cuboidIntBuffer: CuboidIntBuffer, world: KarbonWorld) {
        world.engine.info("Generate chunks: ${cuboidIntBuffer.base} ${cuboidIntBuffer.top}")
        if (cuboidIntBuffer.baseY == 0) {
            cuboidIntBuffer.fillHorizontalLayer(material, 0)
        }
    }
}