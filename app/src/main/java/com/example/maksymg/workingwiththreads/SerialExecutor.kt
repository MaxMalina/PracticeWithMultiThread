package com.example.maksymg.workingwiththreads

import java.util.*
import java.util.concurrent.Executor


internal class SerialExecutor(val executor: Executor) : Executor {
    private val tasks: Queue<Runnable> = ArrayDeque()
    private var active: Runnable? = null

    @Synchronized
    override fun execute(r: Runnable) {
        tasks.add(Runnable {
            try {
                r.run()
            } finally {
                scheduleNext()
            }
        })
        if (active == null) {
            scheduleNext()
        }
    }

    @Synchronized private fun scheduleNext() {
        active = tasks.poll()
        if (active != null) {
            executor.execute(active)
        }
    }
}