package com.neversaydie.andreii.domain.usecases

import com.neversaydie.andreii.domain.executor.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase(
                          val postExecutorThread: Scheduler,
                          val workExecurorThread: Scheduler = Schedulers.io()) {

    constructor(postExecutorThread: PostExecutorThread)
    : this(postExecutorThread.getScheduler())

}