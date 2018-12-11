package com.neversaydie.andreii.namethecapital.presentation.screen.logo

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.databinding.FragmentLogoBinding
import com.neversaydie.andreii.namethecapital.presentation.base.BaseMvvmFragment
import com.neversaydie.andreii.namethecapital.presentation.screen.GameRouter
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_logo.*

class LogoFragment:BaseMvvmFragment<LogoViewModel,GameRouter,FragmentLogoBinding>() {


    override fun provideViewModel(): LogoViewModel {
     return ViewModelProviders.of(this).get(LogoViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_logo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  val buttonGoToGame=buttonGoToGame

        buttonGoToGame.setOnClickListener(View.OnClickListener {
            router?.goToGame()
        })

    }

}