package com.neversaydie.andreii.namethecapital.presentation.screen

import android.arch.lifecycle.ViewModelProviders
import android.content.*
import android.databinding.ObservableField
import android.os.Bundle
import android.os.IBinder
import android.preference.PreferenceManager
import android.support.v4.app.DialogFragment
import android.util.Log
import com.neversaydie.andreii.namethecapital.R
import com.neversaydie.andreii.namethecapital.databinding.ActivityMainBinding
import com.neversaydie.andreii.namethecapital.presentation.app.App
import com.neversaydie.andreii.namethecapital.presentation.base.BaseMvvmActivity
import com.neversaydie.andreii.namethecapital.presentation.util.MyService


class GameMainActivity : BaseMvvmActivity<GameMainViewModel, GameRouter, ActivityMainBinding>() {

    var mBound = false
    var myService: MyService? = null

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
        startService(Intent(this, MyService::class.java))

        if (savedInstanceState == null) {
            router.goToLogo()
            Log.d("myLog", "goTOLogo")
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MyService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (mBound) {
            unbindService(mConnection)
            mBound = false
        }

    }

    private val mConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName,
                                        service: IBinder) {
            val binder = service as MyService.LocalBinder
            myService = binder.service
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onResume() {
        super.onResume()
        stopService(Intent(this, MyService::class.java))
    }
}
