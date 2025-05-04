package com.example.booktrackercompose.screens.settings

import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import javax.inject.Inject
import androidx.core.net.toUri


@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    private val _isDarkTheme = mutableStateOf(false)
    val isDarkTheme: State<Boolean> = _isDarkTheme

    fun setDarkTheme(enabled: Boolean) {
        _isDarkTheme.value = enabled
    }

    fun getEmailIntent(): Intent {
        return Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:".toUri()
            putExtra(Intent.EXTRA_EMAIL, arrayOf("paramo93@bk.ru"))
        }
    }

    fun getShareIntent(): Intent {
        return Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "палвдодлвы")
            putExtra(Intent.EXTRA_TEXT, "Библиотечка")
        }
    }
}