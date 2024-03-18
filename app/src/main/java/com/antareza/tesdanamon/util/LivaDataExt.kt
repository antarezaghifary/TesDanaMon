package com.antareza.tesdanamon.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

fun <T> MutableLiveData<T>.immutable() = this as LiveData<T>

fun <T> MutableStateFlow<T>.immutable() = this.asStateFlow()