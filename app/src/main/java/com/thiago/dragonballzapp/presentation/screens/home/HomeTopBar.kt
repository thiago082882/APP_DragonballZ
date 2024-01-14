package com.thiago.dragonballzapp.presentation.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.thiago.dragonballzapp.R
import com.thiago.dragonballzapp.ui.theme.topAppBarBackgroundColor
import com.thiago.dragonballzapp.ui.theme.topAppBarContentColor


@Composable
fun HomeTopBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Explore",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon)
                )

            }
        }

    )
}

@Preview
@Composable
fun HomeTopBarPreview () {

    HomeTopBar {

    }
    
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeTopBarDarkPreview () {

    HomeTopBar {

    }

}