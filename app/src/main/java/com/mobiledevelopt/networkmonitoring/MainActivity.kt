package com.mobiledevelopt.networkmonitoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.mandy.network_monitoring.Event
import com.mandy.network_monitoring.NetworkEvents
import com.mandy.network_monitoring.NetworkState
import com.mandy.network_monitoring.NetworkStateHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var previousSate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            previousSate = it.getBoolean("LOST_CONNECTION")
        }

        NetworkEvents.observe(this, Observer {
            if (it is Event.ConnectivityEvent)
                handleConnectivityChange(it.state)
        })

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("LOST_CONNECTION", previousSate)
        super.onSaveInstanceState(outState)
    }

    private fun handleConnectivityChange(networkState: NetworkState) {
        if (networkState.isConnected && !previousSate) {
            showSnackBar(textView, "Terhubung Ke Internet")
        }

        if (!networkState.isConnected && previousSate) {
            showSnackBar(textView, "Tidak Ada Koneksi Internet")
        }

        previousSate = networkState.isConnected
    }

    override fun onResume() {
        super.onResume()
        handleConnectivityChange(NetworkStateHolder)
    }

}
