package com.example.booktrackercompose.navigation

import androidx.annotation.DrawableRes
import com.example.booktrackercompose.R

sealed class Screen(val route: String, @DrawableRes val icon: Int) {
    object Library: Screen("library", R.drawable.library_fragment_saved_img)
    object Setting: Screen("settings", R.drawable.bottom_nav_menu_img)
    object Search: Screen("search",R.drawable.search_img)
    object LogIn: Screen("login",R.drawable.img_placeholder)
    object Registration: Screen("registration", R.drawable.img_placeholder)

    fun withBottomBar(): Boolean{
        return when(this){
            LogIn, Registration -> false
            else -> true
        }
    }
}