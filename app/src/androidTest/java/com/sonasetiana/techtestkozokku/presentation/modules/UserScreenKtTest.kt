package com.sonasetiana.techtestkozokku.presentation.modules

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.sonasetiana.techtestkozokku.data.DummyData
import com.sonasetiana.techtestkozokku.domain.UserInteractorTest
import com.sonasetiana.techtestkozokku.domain.modules.user.UserUseCase
import com.sonasetiana.techtestkozokku.presentation.modules.user.UserScreen
import com.sonasetiana.techtestkozokku.presentation.theme.TechTestKozokkuTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class UserScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        val mockedModule = module {
            factory<UserUseCase> { UserInteractorTest() }
        }

        loadKoinModules(mockedModule)
        composeTestRule.setContent {
            TechTestKozokkuTheme {
                UserScreen()
            }
        }
    }

    @Test
    fun verifyDetailScreen() {
        val data = DummyData.userItems.first()
        //verify list
        composeTestRule.onNodeWithTag("UserList").assertIsDisplayed()
        //verify shimmer loading
        composeTestRule.onNodeWithTag("ShimmerGridLoading1").assertIsDisplayed()
        Thread.sleep(1000)
        //verify username
        composeTestRule.onNodeWithText(data.fullName).assertIsDisplayed()
    }
}