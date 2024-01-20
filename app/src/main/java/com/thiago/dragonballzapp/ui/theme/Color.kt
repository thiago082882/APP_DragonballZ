package com.thiago.dragonballzapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Colors

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)
val StarColor = Color(0xFFFFC94D)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)

val Pink80 = Color(0xFFEFB8C8)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val Orange700 = Color(0xFFC9380B)
val Orange500 = Color(0xFFF87C55)

@Composable
fun isLight(): Boolean {
    return !isSystemInDarkTheme()
}

val Colors.statusBarColor: Color
    @Composable
    get() = if (isLight()) Purple700 else Color.Black
val Colors.homeScreenBackgroundColor: Color
    @Composable
    get() = if (isLight()) Color.White else DarkGray

val Colors.welcomeScreenBackgroundColor: Color
    @Composable
    get() = if (isLight()) Color.White else Color.Black

val Colors.titleColor: Color
    @Composable
    get() = if (isLight()) DarkGray else LightGray

val Colors.descriptionColor: Color
    @Composable
    get() = if (isLight()) DarkGray.copy(alpha = 0.5f) else LightGray.copy(alpha = 0.5f)

val Colors.activeIndicatorColor: Color
    @Composable
    get() = if (isLight()) Purple500 else Purple700

val Colors.inactiveIndicatorColor: Color
    @Composable
    get() = if (isLight()) LightGray else DarkGray

val Colors.buttonBackgroundColor: Color
    @Composable
    get() = if (isLight()) Purple500 else Purple700

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight()) Color.White else LightGray

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight()) Purple500 else DarkGray


