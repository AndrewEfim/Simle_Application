package com.neversaydie.andreii.namethecapital.presentation.util

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.app.Service
import android.content.*
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Binder
import android.os.IBinder
import android.preference.PreferenceManager
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.neversaydie.andreii.namethecapital.presentation.app.App


@SuppressLint("Registered")
class MyService : Service() {

    private val myBinder = LocalBinder()
    private val SHARED_CONNECTION = "Connection"
    var sharedPref: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(App.instance)


    inner class LocalBinder : Binder() {
        internal val service: MyService
            get() = this@MyService
    }


    override fun onBind(intent: Intent): IBinder? {
        return myBinder
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(WIFI, intentFilter)
        return super.onStartCommand(intent, flags, startId)
    }

    var WIFI: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
                val noConnect = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
                if (noConnect) {
                   // sharedPref?.edit()?.putBoolean(SHARED_CONNECTION, noConnect)?.apply()
                    Toast.makeText(applicationContext, "Проверьте состояние сети", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Сеть в порядке", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}