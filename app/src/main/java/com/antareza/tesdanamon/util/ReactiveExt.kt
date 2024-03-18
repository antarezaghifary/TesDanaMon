package com.antareza.tesdanamon.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver

fun Lifecycle.addObservers(vararg lifecycleObserver: LifecycleObserver) {
    lifecycleObserver.forEach {
        addObserver(it)
    }
}

fun Fragment.addObservers(vararg lifecycleObserver: LifecycleObserver) {
    viewLifecycleOwner.lifecycle.addObservers(*lifecycleObserver)
}