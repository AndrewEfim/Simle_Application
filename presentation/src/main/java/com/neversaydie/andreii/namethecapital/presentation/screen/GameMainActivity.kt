package com.neversaydie.andreii.namethecapital.presentation.screen

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.databinding.ActivityMainBinding
import com.neversaydie.andreii.namethecapital.presentation.base.BaseMvvmActivity
import com.neversaydie.andreii.namethecapital.presentation.screen.GameMainViewModel
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter

class GameMainActivity : BaseMvvmActivity<GameMainViewModel, GameRouter, ActivityMainBinding>() {


    override fun provideViewModel(): GameMainViewModel {
        return ViewModelProviders.of(this).get(GameMainViewModel::class.java)
    }

    override fun provideRouter(): GameRouter {
        return GameRouter(this)
    }

    override fun provideLayourId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            router.goToLogo()
            Log.d("myLog","goTOLogo")
        }
    }
}