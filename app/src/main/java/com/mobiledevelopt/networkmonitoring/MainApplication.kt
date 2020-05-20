package com.mobiledevelopt.networkmonitoring

import android.app.Application
import com.mandy.network_monitoring.NetworkStateHolder.registerConnectivityBroadcaster

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        registerConnectivityBroadcaster()
    }
}