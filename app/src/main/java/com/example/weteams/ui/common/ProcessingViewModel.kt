package com.example.weteams.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class ProcessingViewModel: ViewModel() {
    private val _isProcessing = MutableLiveData(false)
    val isProcessing: LiveData<Boolean>
        get() = _isProcessing

    protected fun process(f: suspend () -> Unit) {
        _isProcessing.value = true

        viewModelScope.launch {
            try {
                f()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isProcessing.value = false
            }
        }
    }
}
