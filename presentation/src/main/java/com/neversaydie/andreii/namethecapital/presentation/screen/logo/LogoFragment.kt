package com.neversaydie.andreii.namethecapital.presentation.screen.logo

import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.databinding.FragmentLogoBinding
import com.neversaydie.andreii.namethecapital.presentation.app.App
import com.neversaydie.andreii.namethecapital.presentation.base.BaseMvvmFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import com.neversaydie.andreii.namethecapital.presentation.screen.MyDialog
import kotlinx.android.synthetic.main.fragment_logo.*

class LogoFragment : BaseMvvmFragment<LogoViewModel, GameRouter, FragmentLogoBinding>() {

    private val SHARED_COUNTER_RESULT_LVL_ONE = "GAME_RESULT_ONE"
    private val SHARED_COUNTER_RESULT_LVL_TWO = "GAME_RESULT_LVL_TWO"
    private val SHARED_COUNTER_RESULT_LVL_THREE = "GAME_RESULT_LVL_THREE"
    private val SHARED_COUNTER_RESULT_LVL_FOUR = "GAME_RESULT_LVL_FOUR"
    private val SHARED_COUNTER_RESULT_LVL_FIVE = "GAME_RESULT_LVL_FIVE"
    private val SHARED_CONNECTION = "Connection"


    var fragDialog: DialogFragment? = null

    override fun provideViewModel(): LogoViewModel {
        return ViewModelProviders.of(this).get(LogoViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_logo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fragDialog = MyDialog()

        val sharedPreference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.instance)

        val resultLvlOne = (sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_ONE, 0))
        val resultLvlTwo = (sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_TWO, 0))
        val resultLvlThree = (sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_THREE, 0))
        val resultLvlFour = (sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_FOUR, 0))
        val resultLvlFive = (sharedPreference.getInt(SHARED_COUNTER_RESULT_LVL_FIVE, 0))
        val connection: Boolean = (sharedPreference.getBoolean(SHARED_CONNECTION, false))


        val mAdView: AdView = adView
        val adRequest: AdRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)



        textView_title.setOnClickListener({
            // viewModel.setZeroToLevels()
            Log.d("myLog", "viewModel.setZeroToLevels()")
        })

        button_lvl_2.isEnabled = false
        button_lvl_2.visibility = View.GONE
        button_lvl_3.isEnabled = false
        button_lvl_3.visibility = View.GONE
        button_lvl_4.isEnabled = false
        button_lvl_4.visibility = View.GONE
        button_lvl_5.isEnabled = false
        button_lvl_5.visibility = View.GONE

        if (resultLvlOne > 20) {
            button_lvl_2.visibility = View.VISIBLE
            button_lvl_2.isEnabled = true
        }
        if (resultLvlTwo > 20) {
            button_lvl_3.visibility = View.VISIBLE
            button_lvl_3.isEnabled = true
        }
        if (resultLvlThree > 10) {
            button_lvl_4.visibility = View.VISIBLE
            button_lvl_4.isEnabled = true
        }
        if (resultLvlFour > 10) {
            button_lvl_5.visibility = View.VISIBLE
            button_lvl_5.isEnabled = true
        }
//FIXME потом настороить, а может быть удалить
        if (connection == false) {
            Log.d("myLog", "connection" + connection)
            (fragDialog as MyDialog).show(fragmentManager, "fragDialog")//
        }
//
        button_lvl_1.setOnClickListener({
            router?.goToLevelOne()
        })
        button_lvl_2.setOnClickListener({
            router?.goToLevelTwo()
        })
        button_lvl_3.setOnClickListener({
            router?.goToLevelThree()
        })
        button_lvl_4.setOnClickListener({
            router?.goToLevelFour()
        })
        button_lvl_5.setOnClickListener({
            router?.goToLevelFive()
        })


    }

}