package com.karbonpowered.engine.scheduler

import kotlin.time.Duration
import kotlin.time.ExperimentalTime

interface AsyncManager {
    @OptIn(ExperimentalTime::class)
    suspend fun startTickRun(stage: Int, duration: Duration) {
    }

    /**
     * This function is called directly before preSnapshot is called
     */
    suspend fun finalizeRun() {}

    /**
     * This function is called directly before copySnapshotRun and is a MONITOR ONLY stage and no updates should be performed.
     *
     * It occurs after the finalized stage and before the copy snapshot stage.
     */
    suspend fun preSnapshotRun() {}

    /**
     * This function is called in order to update the snapshot at the end of each tick
     */
    fun copySnapshotRun() {}
}