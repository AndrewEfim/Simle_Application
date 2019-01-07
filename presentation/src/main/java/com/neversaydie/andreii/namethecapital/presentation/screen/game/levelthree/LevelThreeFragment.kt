package com.neversaydie.andreii.namethecapital.presentation.screen.game.levelthree

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.databinding.FragmentLevelThreeBinding
import com.neversaydie.andreii.namethecapital.presentation.base.BaseMvvmFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import kotlinx.android.synthetic.main.fragment_level_three.*

class LevelThreeFragment : BaseMvvmFragment<LevelThreeViewModel, GameRouter, FragmentLevelThreeBinding>() {

    override fun provideViewModel(): LevelThreeViewModel {
        return ViewModelProviders.of(this).get(LevelThreeViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_level_three

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        textView_Help_v1_level_three.visibility = View.GONE


        button_Help_level_three.setOnClickListener(View.OnClickListener {
            textView_Help_v1_level_three.visibility = View.VISIBLE
            viewModel.asckHelp()
            if (viewModel.helpCounter_level_three == 3) {
                Log.d("myLog","helpCounter_level_one"+viewModel.helpCounter_level_three)
                button_Help_level_three.isClickable=false
            }
        })

        buttonAnswer_1_level_three.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerOne()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_three.visibility == View.VISIBLE) {
                textView_Help_v1_level_three.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_1")
        })
        buttonAnswer_2_level_three.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerTwo()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_three.visibility == View.VISIBLE) {
                textView_Help_v1_level_three.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_2")
        })
        buttonAnswer_3_level_three.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerThree()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_three.visibility == View.VISIBLE) {
                textView_Help_v1_level_three.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_3")
        })
        buttonAnswer_4_level_three.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerFour()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_three.visibility == View.VISIBLE) {
                textView_Help_v1_level_three.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_4")
        })
    }

}