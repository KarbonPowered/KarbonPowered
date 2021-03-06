package com.karbonpowered.engine

import com.karbonpowered.engine.scheduler.AsyncManager
import com.karbonpowered.engine.scheduler.KarbonScheduler
import com.karbonpowered.engine.snapshot.SnapshotManager
import com.karbonpowered.engine.util.Log
import com.karbonpowered.engine.world.KarbonWorldManager
import com.karbonpowered.event.SimpleEventManager
import kotlin.time.ExperimentalTime
import kotlin.time.TimeMark
import kotlin.time.TimeSource

@OptIn(ExperimentalTime::class)
abstract class KarbonEngine : AsyncManager {
    val snapshotManager = SnapshotManager()
    val scheduler = KarbonScheduler(this).apply {
        addAsyncManager(this@KarbonEngine)
    }
    val worldManager = KarbonWorldManager(this)
    val eventManager = SimpleEventManager()
    lateinit var startTime: TimeMark

    open suspend fun start() {
        startTime = TimeSource.Monotonic.markNow()
        info("Starting Karbon...")
        info("This software is currently in pre-alpha stage")
        info("Some components may have bugs or not work at all.")
        info("Please report any issues to:")
        info("https://github.com/KarbonPowered/Karbon/issues")
        scheduler.runMainTask()
    }

    fun info(info: String) = Log.info("Karbon", info)
    fun error(info: String, throwable: Throwable? = null) = Log.error("Karbon", info, throwable)
}