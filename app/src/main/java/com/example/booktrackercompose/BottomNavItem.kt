package com.example.booktrackercompose

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val title: String,
    @DrawableRes val image: Int,
    val route: String
)
