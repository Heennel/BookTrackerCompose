package com.example.booktrackercompose

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@HiltAndroidApp
@Singleton
class App:Application()