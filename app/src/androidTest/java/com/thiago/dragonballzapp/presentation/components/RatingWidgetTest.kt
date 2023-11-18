package com.thiago.dragonballzapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import com.thiago.dragonballzapp.ui.theme.SMALL_PADDING
import org.junit.Rule
import org.junit.Test

class RatingWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun passZeroPointZeroValue_Assert_FourEmptyStars_and_OneHalFilledStar(){
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all= SMALL_PADDING), rating = 0.0
            )
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
                .assertCountEquals(5)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
    }
    @Test
    fun passZeroPointFiveValue_Assert_FiveEmptyStars(){
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all= SMALL_PADDING), rating = 0.5
            )
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(4)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(1)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
    }
    @Test
    fun passZeroPointFiveValue_Assert_FourEmptyStars_and_OneFilledStart(){
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all= SMALL_PADDING), rating = 0.5
            )
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(4)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(1)
    }
}