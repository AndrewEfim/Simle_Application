package com.neversaydie.andreii.namethecapital.presentation.screen

import android.util.Log
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.presentation.base.BaseRouter
import com.neversaydie.andreii.namethecapital.presentation.screen.game.GameFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.logo.LogoFragment

class GameRouter(activity: GameMainActivity) : BaseRouter<GameMainActivity>(activity) {



    fun goToLogo() {
        replaceFragment(activity.supportFragmentManager
                , LogoFragment()
                , R.id.fragmentConteiner, false)
        Log.d(TAG, "goToLogo")
    }

    fun goToGame() {
        replaceFragment(activity.supportFragmentManager
                , GameFragment()
                , R.id.fragmentConteiner, false)
        Log.d(TAG, "goToGame")
    }


}