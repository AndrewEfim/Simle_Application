package com.neversaydie.andreii.namethecapital.presentation.screen.logo

import android.content.Context
import android.content.SharedPreferences
import android.databinding.ObservableField
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import android.support.annotation.IntegerRes
import android.support.v4.content.ContextCompat.getSystemService
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.neversaydie.andreii.namethecapital.presentation.app.App
import com.neversaydie.andreii.namethecapital.presentation.base.BaseViewModel
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import kotlin.coroutines.experimental.coroutineContext

class LogoViewModel : BaseViewModel<GameRouter>() {
    private val SHARED_COUNTER_RESULT = "GAME_RESULT"
    var sharedPref: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(App.instance)

    var sharedPreference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.instance)
    var result = ObservableField<String>(" ")

    var resultLevelOne = ObservableField<String>(" ")
    var resultLevelTwo = ObservableField<String>(" ")
    var resultLevelThree = ObservableField<String>(" ")
    var resultLevelFour = ObservableField<String>(" ")
    var resultLevelFive = ObservableField<String>(" ")
    var commonResult = ObservableField<String>("")
    init {

    }

    fun setZero() {
        resultLevelOne.set("0")
        resultLevelTwo.set("")
        resultLevelThree.set("")
        resultLevelFour.set("")
        resultLevelFive.set("")
    }

    fun isOnline(): Boolean {
        val cs: String = Context.CONNECTIVITY_SERVICE
        val cm: ConnectivityManager = App.instance.applicationContext.getSystemService(cs) as ConnectivityManager
        if (cm.activeNetworkInfo == null) {
            return false
        } else {
            return false
        }
    }


    fun checkNetConnection() {
        if (isOnline()) {
            Toast.makeText(App.instance.applicationContext, "Интернет подключен", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(App.instance.applicationContext, "Проверьте подключение к интернету", Toast.LENGTH_SHORT).show()
        }
    }
}

