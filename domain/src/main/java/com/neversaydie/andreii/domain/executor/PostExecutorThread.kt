package com.neversaydie.andreii.domain.executor

import io.reactivex.Scheduler

interface PostExecutorThread {
    fun getScheduler():Scheduler
}