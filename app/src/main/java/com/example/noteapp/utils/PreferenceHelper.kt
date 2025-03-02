package com.example.noteapp.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var onBoardShown: Boolean
        get() = sharedPreferences.getBoolean("onboard", false)
        set(value) = sharedPreferences.edit().putBoolean("onboard", value).apply()

    var authShown: Boolean
        get() = sharedPreferences.getBoolean("onAuth",false)
        set(value) = sharedPreferences.edit().putBoolean("onboard",value).apply()
}
