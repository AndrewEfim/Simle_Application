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
    private val SHARED_COUNTER_RESULT_LVL_ONE = "GAME_RESULT_ONE"
    private val SHARED_COUNTER_RESULT_LVL_TWO = "GAME_RESULT_LVL_TWO"
    private val SHARED_COUNTER_RESULT_LVL_THREE = "GAME_RESULT_LVL_THREE"
    private val SHARED_COUNTER_RESULT_LVL_FOUR = "GAME_RESULT_LVL_FOUR"
    private val SHARED_COUNTER_RESULT_LVL_FIVE = "GAME_RESULT_LVL_FIVE"
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
        // val newResult: Int = sharedPreference.getInt(SHARED_COUNTER_RESULT, 0)
        // if (Integer.parseInt(result.get().toString()) < newResult) {
        //result.set(sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_ONE, 0).toString())

        resultLevelOne.set(sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_ONE, 0).toString())
        resultLevelTwo.set(sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_TWO, 0).toString())
        resultLevelThree.set(sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_THREE, 0).toString())
        resultLevelFour.set(sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_FOUR, 0).toString())
        resultLevelFive.set(sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_FIVE, 0).toString())

        commonResult.set((sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_ONE, 0)
                + sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_TWO, 0)
                + sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_THREE, 0)
                + sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_FOUR, 0)
                + sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_FIVE, 0)).toString()
        )


// FIXME       if (Integer.parseInt(result.get().toString()) < sharedPreference.getInt(SHARED_COUNTER_RESULT, 0)) {
//            result.set(sharedPreference.getInt(SHARED_COUNTER_RESULT, 0).toString())
//        }

    }

    fun setZeroToLevels() {
        sharedPref?.edit()?.putInt(SHARED_COUNTER_RESULT_LVL_ONE, 0)?.apply()
        sharedPref?.edit()?.putInt(SHARED_COUNTER_RESULT_LVL_TWO, 0)?.apply()
        sharedPref?.edit()?.putInt(SHARED_COUNTER_RESULT_LVL_THREE, 0)?.apply()
        sharedPref?.edit()?.putInt(SHARED_COUNTER_RESULT_LVL_FOUR, 0)?.apply()
        sharedPref?.edit()?.putInt(SHARED_COUNTER_RESULT_LVL_FIVE, 0)?.apply()
        resultLevelOne.set(sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_ONE, 0).toString())
        resultLevelTwo.set(sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_TWO, 0).toString())
        resultLevelThree.set(sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_THREE, 0).toString())
        resultLevelFour.set(sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_FOUR, 0).toString())
        resultLevelFive.set(sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_FIVE, 0).toString())

    }
}

