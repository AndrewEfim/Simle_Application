package com.neversaydie.andreii.namethecapital.presentation.screen.game.gameresult

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.databinding.FragmentGameResultBinding
import com.neversaydie.andreii.namethecapital.presentation.base.BaseMvvmFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import kotlinx.android.synthetic.main.fragment_game_result.*

class GameResultFragment: BaseMvvmFragment<GameResultViewModel, GameRouter, FragmentGameResultBinding>() {


    override fun provideViewModel(): GameResultViewModel {
    return ViewModelProviders.of(this).get(GameResultViewModel::class.java)

    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_game_result
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_game_resul_fragment_go_start.setOnClickListener({
            router?.goToLogo()
        })
        button_game_resul_set_zero.setOnClickListener({
            viewModel.setZero()
            router?.goToLogo()
        })
    }
}