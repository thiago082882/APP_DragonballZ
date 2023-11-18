package com.thiago.dragonballzapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performMouseInput
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {
    @get:Rule
    val composeTextRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputText_assertInputText() {
        val text = mutableStateOf("")
        composeTextRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onCloseClicked = {},
                onSearchClicked ={}
            )
        }
        composeTextRule.onNodeWithContentDescription("TextField")
            .performTextInput("thiago-costa")
        composeTextRule.onNodeWithContentDescription("TextField")
            .assertTextEquals("thiago-costa")
    }
    @Test
    fun openSearchWidget_addInputText_pressCloseButton_assertEmptyInputText() {
        val text = mutableStateOf("")
        composeTextRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onCloseClicked = {},
                onSearchClicked ={}
            )
        }
        composeTextRule.onNodeWithContentDescription("TextField")
            .performTextInput("thiago-costa")
        composeTextRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTextRule.onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }
    @Test
    fun openSearchWidget_addInputText_pressCloseButtonTwice_assertClosedState() {
        val text = mutableStateOf("")
        val searchWidgetShow = mutableStateOf(true)
        composeTextRule.setContent {
            if(searchWidgetShow.value) {
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onCloseClicked = { searchWidgetShow.value = false },
                    onSearchClicked = {}
                )
            }
        }
        composeTextRule.onNodeWithContentDescription("TextField")
            .performTextInput("thiago-costa")
        composeTextRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTextRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTextRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }

    @Test
    fun openSearchWidget_pressCloseButtonOnceWhenInputIsEmpty_assertClosedState() {
        val text = mutableStateOf("")
        val searchWidgetShow = mutableStateOf(true)
        composeTextRule.setContent {
            if(searchWidgetShow.value) {
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onCloseClicked = { searchWidgetShow.value = false },
                    onSearchClicked = {}
                )
            }
        }

        composeTextRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTextRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }
}