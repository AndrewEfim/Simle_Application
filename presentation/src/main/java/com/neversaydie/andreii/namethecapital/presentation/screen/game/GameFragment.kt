package com.neversaydie.andreii.namethecapital.presentation.screen.game

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.databinding.FragmentGameBinding
import com.neversaydie.andreii.namethecapital.presentation.base.BaseMvvmFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : BaseMvvmFragment<GameViewModel, GameRouter, FragmentGameBinding>() {


    override fun provideViewModel(): GameViewModel {
        return ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_game
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var buttonCheckAnswer=buttonCheckAnswer

        textView_Help.visibility=View.GONE



        buttonCheckAnswer.setOnClickListener(View.OnClickListener {
            if(textView_Help.visibility==View.VISIBLE){
                textView_Help.visibility=View.GONE
            }
            viewModel.checkAnswer()

            Log.d("myLog","buttonCheckAnswer.setOnClickListener")
        })

        buttonHelp.setOnClickListener(View.OnClickListener {
            textView_Help.visibility= View.VISIBLE
            viewModel.asckHelp()
        })

    }

}