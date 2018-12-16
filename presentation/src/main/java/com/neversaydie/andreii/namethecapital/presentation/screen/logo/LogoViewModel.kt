package com.neversaydie.andreii.namethecapital.presentation.screen.logo

import android.content.SharedPreferences
import android.databinding.ObservableField
import android.preference.PreferenceManager
import android.support.annotation.IntegerRes
import android.util.Log
import android.widget.Button
import com.neversaydie.andreii.namethecapital.presentation.app.App
import com.neversaydie.andreii.namethecapital.presentation.base.BaseViewModel
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter

class LogoViewModel : BaseViewModel<GameRouter>() {
    private val SHARED_COUNTER_RESULT = "GAME_RESULT"

    var sharedPreference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.instance)
    var result = ObservableField<String>(" ")

    init {
        // val newResult: Int = sharedPreference.getInt(SHARED_COUNTER_RESULT, 0)
        // if (Integer.parseInt(result.get().toString()) < newResult) {
        result.set(sharedPreference.getInt(SHARED_COUNTER_RESULT, 0).toString())
// FIXME       if (Integer.parseInt(result.get().toString()) < sharedPreference.getInt(SHARED_COUNTER_RESULT, 0)) {
//            result.set(sharedPreference.getInt(SHARED_COUNTER_RESULT, 0).toString())
//        }

    }
}

