package com.neversaydie.andreii.namethecapital.presentation.screen.game.levelone

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.databinding.FragmentLevelOneBinding
import com.neversaydie.andreii.namethecapital.presentation.base.BaseMvvmFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import kotlinx.android.synthetic.main.fragment_level_one.*

class LevelOneFragment : BaseMvvmFragment<LevelOneViewModel, GameRouter, FragmentLevelOneBinding>() {

    override fun provideViewModel(): LevelOneViewModel {
        return ViewModelProviders.of(this).get(LevelOneViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_level_one

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        textView_Help_v1_level_one.visibility = View.GONE


        button_Help_level_one.setOnClickListener(View.OnClickListener {
            textView_Help_v1_level_one.visibility = View.VISIBLE
            viewModel.asckHelp()
        })

        buttonAnswer_1_level_one.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerOne()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_one.visibility == View.VISIBLE) {
                textView_Help_v1_level_one.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_1")
        })
        buttonAnswer_2_level_one.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerTwo()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_one.visibility == View.VISIBLE) {
                textView_Help_v1_level_one.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_2")
        })
        buttonAnswer_3_level_one.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerThree()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_one.visibility == View.VISIBLE) {
                textView_Help_v1_level_one.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_3")
        })
        buttonAnswer_4_level_one.setOnClickListener(View.OnClickListener {
            viewModel.setAnswerFour()
            viewModel.checkAnswer()
            if (textView_Help_v1_level_one.visibility == View.VISIBLE) {
                textView_Help_v1_level_one.visibility = View.GONE
            }

            Log.d("myLog", "buttonAnswer_4")
        })
    }

}