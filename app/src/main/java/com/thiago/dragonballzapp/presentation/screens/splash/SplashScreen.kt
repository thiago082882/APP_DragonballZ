package com.thiago.dragonballzapp.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.thiago.dragonballzapp.R
import com.thiago.dragonballzapp.navigation.Screen
import com.thiago.dragonballzapp.ui.theme.Orange500
import com.thiago.dragonballzapp.ui.theme.Orange700

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {

    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()
    val degrees = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if (onBoardingCompleted) {
            navController.navigate(Screen.Home.route)
        } else {
            navController.navigate(Screen.Welcome.route)
        }
    }
    Splash(degrees = degrees.value)
}

@Composable
fun Splash(degrees: Float) {
    val modifier = if (isSystemInDarkTheme()) {
        Modifier.background(Color.Black)
    } else {
        Modifier.background(
            Brush.verticalGradient(listOf(Orange700, Orange500))
        )
    }
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.rotate(degrees = degrees),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.app_logo)
            )
        }



}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(degrees = 0f)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Splash(degrees = 0f)
}