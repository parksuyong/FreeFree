package com.kindfriends.freefree.util

import android.os.Handler
import android.os.Message
import java.lang.ref.WeakReference

/**
 * Created by qkrtndyd1234 on 2017. 10. 14..
 */
abstract class WeakReferenceHandler<T>: Handler {
    private var mReference: WeakReference<T>? = null

    constructor(){
        this.mReference = null
    }

    constructor(reference: T?) : this(){
        this.mReference = WeakReference<T>(reference)
    }

    override fun handleMessage(msg: Message) {
        val reference : T? = mReference?.get()

        handleMessage(reference, msg)
    }

    protected abstract fun handleMessage(mReference:T?, msg: Message)
}