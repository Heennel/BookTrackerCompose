package com.example.booktrackercompose

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            title = "Поиск",
            image = R.drawable.search_img,
            route = "search"
        ),
        BottomNavItem(
            title = "Библиотека",
            image = R.drawable.library_fragment_saved_img,
            route = "library"
        ),
        BottomNavItem(
            title = "Настройки",
            image = R.drawable.bottom_nav_menu_img,
            route = "settings"
        )
    )
}