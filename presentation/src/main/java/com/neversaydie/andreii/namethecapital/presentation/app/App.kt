package com.neversaydie.andreii.namethecapital.presentation.app

import android.app.Application

class App :Application() {

    companion object {
        lateinit var instance: App
    }

    init {
        instance = this
    }
}