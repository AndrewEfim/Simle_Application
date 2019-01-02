package com.neversaydie.andreii.namethecapital.presentation.screen.game.levelfive

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.databinding.FragmentLevelThreeBinding
import com.neversaydie.andreii.namethecapital.databinding.FragmentLevelTwoBinding
import com.neversaydie.andreii.namethecapital.presentation.base.BaseMvvmFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import kotlinx.android.synthetic.main.fragment_level_five.*
import kotlinx.android.synthetic.main.fragment_level_four.*
import kotlinx.android.synthetic.main.fragment_level_three.*
import kotlinx.android.synthetic.main.fragment_level_two.*

class LevelFiveFragment : BaseMvvmFragment<LevelFiveViewModel, GameRouter, FragmentLevelThreeBinding>() {

    override fun provideViewModel(): LevelFiveViewModel {
        return ViewModelProviders.of(this).get(LevelFiveViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_level_five

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        textView_Help_v1_level_five.visibility = View.GONE


        button_Help_level_five.setOnClickListener(View.OnClickListener {
            textView_Help_v1_level_five.visibility = View.VISIBLE
            viewModel.asckHelp()
        })

        buttonAnswer_1_level_five.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerOne()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_five.visibility == View.VISIBLE) {
                textView_Help_v1_level_five.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_1")
        })
        buttonAnswer_2_level_five.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerTwo()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_five.visibility == View.VISIBLE) {
                textView_Help_v1_level_five.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_2")
        })
        buttonAnswer_3_level_five.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerThree()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_five.visibility == View.VISIBLE) {
                textView_Help_v1_level_five.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_3")
        })
        buttonAnswer_4_level_five.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerFour()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_five.visibility == View.VISIBLE) {
                textView_Help_v1_level_five.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_4")
        })
    }

}