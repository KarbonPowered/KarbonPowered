package com.karbonpowered.engine.util.cuboid

import com.karbonpowered.math.vector.IntVector3

class EmptyCuboidIntBuffer(base: IntVector3, size: IntVector3) : CuboidIntBuffer(base, size, intArrayOf()) {
    override fun get(x: Int, y: Int, z: Int): Int = 0

    override fun set(x: Int, y: Int, z: Int, value: Int) {
    }

    override fun fill(value: Int) {
    }

    override fun fillHorizontalLayer(value: Int, y: Int, height: Int) {
    }

    override fun copyElement(thisIndex: Int, sourceIndex: Int, runLength: Int, source: CuboidBuffer) {
    }
}
