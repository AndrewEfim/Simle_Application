package com.neversaydie.andreii.namethecapital.presentation.executor

import com.neversaydie.andreii.domain.executor.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutorThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}