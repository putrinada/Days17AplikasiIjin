package com.adl.days17aplikasiijin.background

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

class BootDeviceRecievers : BroadcastReceiver(){
    override fun onReceive(context : Context?, intent: Intent?) {
        context?.let {
            ContextCompat.startForegroundService(it,Intent(it,LocationService::class.java))
        }

    }

}