package com.mandy.network_monitoring

import androidx.lifecycle.LiveData

object NetworkEvents : LiveData<Event>(){

    internal fun notify(event: Event){
        postValue(event)
    }
}