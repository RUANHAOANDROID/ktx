package com.hao.kexpand

import kotlinx.coroutines.*

/**
 * TODO
 * @date Date: 2020/9/29
 * @author 锅得铁
 * @since v1.0
 */
class CoroutinesExpand {
    fun CoroutineScope.launchIO(
        block: suspend () -> Unit,
        error: suspend (Throwable) -> Unit = { it.printStackTrace() }
    ): Job {
        return launch(Dispatchers.IO) {
            try {
                block()
            } catch (e: Throwable) {
                error(e)
                e.printStackTrace()
            }
        }
    }
}

/**
 * 顶级扩展 协程调度至主线程
 * @param block suspend code
 */
suspend fun runMainThread(
    block: suspend () -> Unit
) = withContext(Dispatchers.Main) {
    block()
}

/**
 * 顶级扩展 协程调度至IO线程
 */
suspend fun runIOThread(
    block: suspend () -> Unit
) = withContext(Dispatchers.IO) {
    block()
}