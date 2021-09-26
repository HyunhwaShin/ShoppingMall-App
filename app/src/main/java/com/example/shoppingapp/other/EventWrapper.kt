package com.example.shoppingapp.other

import androidx.lifecycle.Observer

class EventWrapper<out T>(private val content : T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled){
            null
        }else{
            hasBeenHandled = true
            content
        }
    }

    fun peekContent() : T = content
}

class EventObserver<T>(private val onEventUnhandledContent : (T) ->Unit ): Observer<EventWrapper<T>>{
    override fun onChanged(event: EventWrapper<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}


