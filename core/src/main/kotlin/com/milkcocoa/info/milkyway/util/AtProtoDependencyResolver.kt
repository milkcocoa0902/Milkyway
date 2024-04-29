package com.milkcocoa.info.milkyway.util

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

abstract class AtProtoDependencyResolver {
    protected var init = false

    protected abstract fun installDependencies()

    protected val lock by lazy { ReentrantLock() }

    init {
        if(init.not()){
            lock.withLock {
                if (init.not()) {
                    init = true
                    installDependencies()
                }
            }
        }
    }
}