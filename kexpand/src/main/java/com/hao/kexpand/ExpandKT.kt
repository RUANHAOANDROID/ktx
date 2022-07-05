package com.hao.kexpand

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.hao.kexpand.data.DateConverters
import java.util.*

/**
 * TODO
 * @date 2021/7/5
 * @author 锅得铁
 * @since v1.0
 */

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings"
)

fun Date.string(): String {
    return DateConverters.date2Str(this)
}

fun String.date(): Date {
    return DateConverters.str2Date(this)
}

fun Date.calendar(): Calendar {
    val instance = Calendar.getInstance()
    instance.time = this
    return instance
}

fun String.getObjectTime(): Date {
    val time = "${Integer.parseInt(this.substring(0, 8), 16)}000".toLong()
    return Date(time)
}
