package br.ind.siam.alcinolago.maleta.core.preference

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class PreferenceRepositoryImpl(private val context: Context) : PreferenceRepository {

    private val userData: SharedPreferences
        get() = context.getSharedPreferences(PreferenceRepository.USER_DATA, Context.MODE_PRIVATE)

    private val preventData: SharedPreferences
        get() = context.getSharedPreferences(PreferenceRepository.PREVENT_DATA, Context.MODE_PRIVATE)

    override var userIsFirstAccess: Boolean
        get() = userData.getBoolean(USER_DATA_FIRST_ACCESS, false)
        set(system) = setValue(userData, USER_DATA_FIRST_ACCESS, system, "userIsFirstAccess")

    override var preventRepeatData: Int
        get() = preventData.getInt(PREVENT_REPEAT_DATA, 0)
        set(system) = setValue(preventData, PREVENT_REPEAT_DATA, system, "preventRepeatData")

    override fun cleanAll() {
        Log.i(TAG, "Cleaning all preferences")
        cleanAll("")
    }

    override fun cleanAll(vararg exceptions: String) {
        Log.i(TAG, "Cleaning all preferences | exceptions: ${exceptions.toList()}")

        if (!exceptions.contains(PreferenceRepository.USER_DATA)) userData.edit().clear().apply()
    }

    private fun <T> setValue(preferences: SharedPreferences, key: String, value: T?, logName: String = "") {
        Log.i(TAG, "Saving $logName: $value")

        preferences.edit().apply {
            if (value == null)
                remove(key)
            else
                when (value) {
                    is String -> putString(key, value)
                    is Boolean -> putBoolean(key, value)
                    is Int -> putInt(key, value)
                }
        }.apply()
    }

    companion object {
        private const val TAG = "[LOG]Preferences"
        private const val USER_DATA_FIRST_ACCESS = "userDataFirstAccess"
        private const val PREVENT_REPEAT_DATA = "prevent_repeat_data"
    }
}