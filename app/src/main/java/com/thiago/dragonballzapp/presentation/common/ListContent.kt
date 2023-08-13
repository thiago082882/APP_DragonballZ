package com.thiago.dragonballzapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.thiago.dragonballzapp.R
import com.thiago.dragonballzapp.domain.model.Hero
import com.thiago.dragonballzapp.navigation.Screen
import com.thiago.dragonballzapp.presentation.components.RatingWidget
import com.thiago.dragonballzapp.presentation.components.ShimmerEffect
import com.thiago.dragonballzapp.ui.theme.DEFAULT_PADDING
import com.thiago.dragonballzapp.ui.theme.HERO_ITEM_HEIGHT
import com.thiago.dragonballzapp.ui.theme.LARGE_PADDING
import com.thiago.dragonballzapp.ui.theme.MEDIUM_PADDING
import com.thiago.dragonballzapp.ui.theme.SMALL_PADDING
import com.thiago.dragonballzapp.ui.theme.SUPER_EXTRA_SMALL_PADDING
import com.thiago.dragonballzapp.ui.theme.topAppBarContentColor
import com.thiago.dragonballzapp.util.Constants.BASE_URL


@ExperimentalCoilApi
@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>,
    navController: NavHostController
) {
    val result = handlePagingResult(heroes = heroes)
    if(result){
        Log.e("ListContent", heroes.loadState.toString())
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = heroes,
                key = { hero ->
                    hero.id
                }
            ){hero->
                hero?.let {
                    HeroItem(hero = it, navController = navController)
                }
            }
        }
    }

}

@Composable
fun handlePagingResult(heroes : LazyPagingItems<Hero>) : Boolean {

    heroes.apply {
      val error = when{
          loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
          loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
          loadState.append is LoadState.Error -> loadState.append as LoadState.Error
          else -> null
      }
        return when{
            loadState.refresh is LoadState.Loading ->{
                ShimmerEffect()
                false
            }
            error != null  -> {
                EmptyScreen(error = error)
                false
            }
            heroes.itemCount < 1 ->{
                EmptyScreen()
                false
            }
            else -> true
        }
    }


    
}
@ExperimentalCoilApi
@Composable
fun HeroItem(
    hero: Hero,
    navController: NavHostController
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = "$BASE_URL${hero.image}")
            .apply(block = fun ImageRequest.Builder.() {
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_placeholder)
            }).build()
    )
    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Details.passHeroId(heroId = hero.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.hero_image),
                contentScale = ContentScale.Crop
            )

        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.35f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = hero.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(SUPER_EXTRA_SMALL_PADDING))
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = DEFAULT_PADDING),
                        rating = hero.rating
                    )
                    Text(
                        text = "(${hero.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    }
}
@ExperimentalCoilApi
@Preview
@Composable
fun HeroItemPreview() {
    HeroItem(
        hero = Hero(
            id = 1,
            name = "Goku",
            image = "https://static.wikia.nocookie.net/animeverso/images/b/bf/Basegoku.png/revision/latest/scale-to-width-down/347?cb=20210604135906&path-prefix=pt-br",
            about = "Some random text...",
            rating = 4.5,
            power = 100,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ), navController = rememberNavController()
    )

}
@ExperimentalCoilApi
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HeroItemDarkPreview() {
    HeroItem(
        hero = Hero(
            id = 1,
            name = "Goku",
            image = "https://static.wikia.nocookie.net/animeverso/images/b/bf/Basegoku.png/revision/latest/scale-to-width-down/347?cb=20210604135906&path-prefix=pt-br",
            about = "Some random text...",
            rating = 4.5,
            power = 100,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ), navController = rememberNavController()
    )

}
