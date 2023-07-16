package com.thiago.dragonballzapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext


val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val Orange700 = Color(0xFFC9380B)
val Orange500 = Color(0xFFF87C55)

val lightGray = Color(0xFFD8D8D8)
val darkGray = Color(0xFF2A2A2A)
val StarColor = Color(0xFFFFC94D)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)


@Composable
fun getWelcomeScreenBgColor(): Color {

    val configuration = LocalConfiguration.current
    val isLight =
        configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK == android.content.res.Configuration.UI_MODE_NIGHT_NO

    return if (isLight) White else Color.Black
}

val Colors.welcomeScreenBgColor: Color
    @Composable
    get() = getWelcomeScreenBgColor()

@Composable
fun getbuttonBgColor(): Color {

    val configuration = LocalConfiguration.current
    val isLight =
        configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK == android.content.res.Configuration.UI_MODE_NIGHT_NO

    return if (isLight) Orange500 else Orange700
}
val Colors.buttonBgColor
    @Composable
    get() = getbuttonBgColor()


@Composable
fun getactiveIndicatorColor(): Color {

    val configuration = LocalConfiguration.current
    val isLight =
        configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK == android.content.res.Configuration.UI_MODE_NIGHT_NO

    return if (isLight) Orange500 else Orange700
}
val Colors.activeIndicatorColor
    @Composable
    get() = getactiveIndicatorColor()



@Composable
fun getinactiveIndicatorColor(): Color {

    val configuration = LocalConfiguration.current
    val isLight =
        configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK == android.content.res.Configuration.UI_MODE_NIGHT_NO

    return if (isLight) LightGray else DarkGray
}
val Colors.inactiveIndicatorColor
    @Composable
    get() = getinactiveIndicatorColor()



val Colors.splashScreenBackground
    @Composable
    get() = if (isLight) Brush.verticalGradient(
        listOf(
            Purple700,
            Purple500
        )
    ) else Brush.verticalGradient(
        listOf(
            Color.Black,
            Color.Black
        )
    )

val Colors.statusBarColor
    @Composable
    get() = if (isLight) Purple700 else Color.Black


val Colors.titleColor
    @Composable
    get() = if (isLight) DarkGray else LightGray

val Colors.descriptionColor
    @Composable
    get() = if (isLight) DarkGray.copy(alpha = 0.5f) else LightGray.copy(alpha = 0.5f)



@Composable
fun gettopAppBarContentColor(): Color {

    val configuration = LocalConfiguration.current
    val isLight =
        configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK == android.content.res.Configuration.UI_MODE_NIGHT_NO

    return if (isLight) Color.White else LightGray
}

val Colors.topAppBarContentColor
    @Composable
    get() = gettopAppBarContentColor()


@Composable
fun gettopAppBarBgColor(): Color {

    val configuration = LocalConfiguration.current
    val isLight =
        configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK == android.content.res.Configuration.UI_MODE_NIGHT_NO

    return if (isLight) Purple500 else darkGray
}

val Colors.topAppBarBgColor
    @Composable
    get() = gettopAppBarBgColor()

val Colors.shimmerItemBgColor
    @Composable
    get() = if (isLight) ShimmerLightGray else Color.Black

val Colors.shimmerItemContentColor
    @Composable
    get() = if (isLight) ShimmerMediumGray else ShimmerDarkGray

val Colors.emptyScreenContentColor
    @Composable
    get() = if (isLight) DarkGray else LightGray