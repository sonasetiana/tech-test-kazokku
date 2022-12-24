package com.sonasetiana.techtestkozokku.presentation.activity

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.sonasetiana.techtestkozokku.R
import com.sonasetiana.techtestkozokku.presentation.navigation.Screen
import com.sonasetiana.techtestkozokku.presentation.theme.TechTestKozokkuTheme
import com.sonasetiana.techtestkozokku.presentation.utils.assertCurrentRouteName
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()


    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            TechTestKozokkuTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                MainScreen(
                    navController = navController
                )
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Post.route)
    }

    @Test
    fun navHost_verifyUserScreenDisplayed() {
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.menu_user)).performClick()
        navController.assertCurrentRouteName(Screen.User.route)
    }

    @Test
    fun navHost_verifyFavoriteScreenDisplayed() {
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.menu_favorite)).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
    }


}