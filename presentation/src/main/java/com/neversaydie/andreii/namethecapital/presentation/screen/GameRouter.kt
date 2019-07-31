package com.neversaydie.andreii.namethecapital.presentation.screen

import android.util.Log
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.presentation.base.BaseRouter
import com.neversaydie.andreii.namethecapital.presentation.screen.game.GameFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.game.gameresult.GameResultFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.game.levelfive.LevelFiveFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.game.levelfour.LevelFourFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.game.levelone.LevelOneFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.game.levelthree.LevelThreeFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.game.leveltwo.LevelTwoFragment

import com.neversaydie.andreii.namethecapital.presentation.screen.logo.LogoFragment

class GameRouter(activity: GameMainActivity) : BaseRouter<GameMainActivity>(activity) {


    fun goToLogo() {
        replaceFragment(activity.supportFragmentManager
                , LogoFragment()
                , R.id.fragmentConteiner, false)
    }

    fun goToGame() {
        replaceFragment(activity.supportFragmentManager
                , GameFragment()
                , R.id.fragmentConteiner, false)
    }

    fun goToLevelOne() {
        replaceFragment(activity.supportFragmentManager
                , LevelOneFragment()
                , R.id.fragmentConteiner, false)
    }

    fun goToLevelTwo() {
        replaceFragment(activity.supportFragmentManager
                , LevelTwoFragment()
                , R.id.fragmentConteiner, false)
    }

    fun goToLevelThree() {
        replaceFragment(activity.supportFragmentManager
                , LevelThreeFragment()
                , R.id.fragmentConteiner, false)
    }

    fun goToLevelFour() {
        replaceFragment(activity.supportFragmentManager
                , LevelFourFragment()
                , R.id.fragmentConteiner, false)
    }

    fun goToLevelFive() {
        replaceFragment(activity.supportFragmentManager
                , LevelFiveFragment()
                , R.id.fragmentConteiner, false)
    }

    fun goToGameResult() {
        replaceFragment(activity.supportFragmentManager
                , GameResultFragment()
                , R.id.fragmentConteiner, false)
    }

}