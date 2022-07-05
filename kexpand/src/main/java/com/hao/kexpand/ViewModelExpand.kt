package com.hao.kexpand

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

/**
 * TODO
 * @date 2021/8/9
 * @author 锅得铁
 * @since v1.0
 */
fun ViewModel.launchIO(
    block: suspend () -> Unit,
    error: suspend (Throwable) -> Unit = { it.printStackTrace() }
) = viewModelScope.launch(Dispatchers.IO) {
    try {
        block()
    } catch (e: Throwable) {
        error(e)
        e.printStackTrace()
    }
}

fun ViewModel.launch(
    block: suspend () -> Unit,
    error: suspend (Throwable) -> Unit = { it.printStackTrace() }
) = viewModelScope.launch() {
    try {
        block()
    } catch (e: Throwable) {
        error(e)
        e.printStackTrace()
    }
}

@kotlinx.coroutines.ObsoleteCoroutinesApi
fun ViewModel.launchNewThread(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
    viewModelScope.launch(
        newSingleThreadContext("rh_newThread")
    ) {
        try {
            block()
        } catch (e: Throwable) {
            error(e)
            e.printStackTrace()
        }
    }
