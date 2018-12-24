package com.neversaydie.andreii.namethecapital.presentation.screen.game

import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.databinding.FragmentGameV1Binding
import com.neversaydie.andreii.namethecapital.presentation.app.App
import com.neversaydie.andreii.namethecapital.presentation.base.BaseMvvmFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game_v1.*

class GameFragment : BaseMvvmFragment<GameViewModel, GameRouter, FragmentGameV1Binding>() {


    override fun provideViewModel(): GameViewModel {
        return ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_game_v1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.instance)

        val buttonCheckAnswer = buttonCheckAnswer

        textView_Help_v1.visibility = View.GONE

        button_Help.setOnClickListener(View.OnClickListener {
            textView_Help_v1.visibility = View.VISIBLE
            viewModel.asckHelp()
        })

        buttonAnswer_1.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerOne()
            viewModel.checkAnswer()
            if (textView_Help_v1.visibility == View.VISIBLE) {
                textView_Help_v1.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_1")
        })
        buttonAnswer_2.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerTwo()
            viewModel.checkAnswer()
            if (textView_Help_v1.visibility == View.VISIBLE) {
                textView_Help_v1.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_2")
        })
        buttonAnswer_3.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerThree()
            viewModel.checkAnswer()
            if (textView_Help_v1.visibility == View.VISIBLE) {
                textView_Help_v1.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_3")
        })
        buttonAnswer_4.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerFour()
            viewModel.checkAnswer()
            if (textView_Help_v1.visibility == View.VISIBLE) {
                textView_Help_v1.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_4")
        })

    }

}