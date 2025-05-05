package com.example.booktrackercompose

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@HiltAndroidApp
@Singleton
class App:Application() {

    private val sharedPreferences: SharedPreferences by lazy {getSharedPreferences("BookTrackerCompose",
        MODE_PRIVATE)}

    override fun onCreate() {
        super.onCreate()
        changeTheme(sharedPreferences.getBoolean("THEME_KEY",false))
    }

    fun changeTheme(enable: Boolean){
        val mode = if(enable) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
        sharedPreferences.edit() { putBoolean("THEME_KEY", enable) }
    }
}