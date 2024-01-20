package com.thiago.dragonballzapp.presentation.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.thiago.dragonballzapp.navigation.Screen
import com.thiago.dragonballzapp.presentation.common.ListContent
import com.thiago.dragonballzapp.ui.theme.homeScreenBackgroundColor
import com.thiago.dragonballzapp.ui.theme.statusBarColor



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    val systemBarColor = MaterialTheme.colors.statusBarColor.toArgb()
 SideEffect {

  activity.window.statusBarColor = systemBarColor
 }

    Scaffold(
        backgroundColor = MaterialTheme.colors.homeScreenBackgroundColor,
        topBar = {
            HomeTopBar(onSearchClicked = { navController.navigate(Screen.Search.route) })
        },
        content = {
            ListContent(
                heroes = allHeroes,
                navController=navController
            )
        }
    )

}