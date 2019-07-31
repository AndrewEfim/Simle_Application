package com.neversaydie.andreii.namethecapital.presentation.screen.game.leveltwo

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.databinding.FragmentLevelTwoBinding
import com.neversaydie.andreii.namethecapital.presentation.base.BaseMvvmFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import kotlinx.android.synthetic.main.fragment_level_two.*

class LevelTwoFragment : BaseMvvmFragment<LevelTwoViewModel, GameRouter, FragmentLevelTwoBinding>() {

    override fun provideViewModel(): LevelTwoViewModel {
        return ViewModelProviders.of(this).get(LevelTwoViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_level_two

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        textView_Help_v1_level_two.visibility = View.GONE


        button_Help_level_two.setOnClickListener(View.OnClickListener {
            textView_Help_v1_level_two.visibility = View.VISIBLE
            viewModel.asckHelp()
            if (viewModel.helpCounter_level_two == 3) {
                button_Help_level_two.isClickable = false
            }
        })

        buttonAnswer_1_level_two.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerOne()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_two.visibility == View.VISIBLE) {
                textView_Help_v1_level_two.visibility = View.GONE
            }
        })
        buttonAnswer_2_level_two.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerTwo()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_two.visibility == View.VISIBLE) {
                textView_Help_v1_level_two.visibility = View.GONE
            }
        })
        buttonAnswer_3_level_two.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerThree()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_two.visibility == View.VISIBLE) {
                textView_Help_v1_level_two.visibility = View.GONE
            }
        })
        buttonAnswer_4_level_two.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerFour()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_two.visibility == View.VISIBLE) {
                textView_Help_v1_level_two.visibility = View.GONE
            }
        })
    }
}