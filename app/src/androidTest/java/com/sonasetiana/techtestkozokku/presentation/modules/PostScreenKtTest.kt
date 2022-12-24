package com.sonasetiana.techtestkozokku.presentation.modules

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.sonasetiana.techtestkozokku.data.DummyData
import com.sonasetiana.techtestkozokku.domain.TimeLineInteractorTest
import com.sonasetiana.techtestkozokku.domain.modules.timeline.TimeLineUseCase
import com.sonasetiana.techtestkozokku.presentation.modules.timeline.TimeLineScreen
import com.sonasetiana.techtestkozokku.presentation.theme.TechTestKozokkuTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class PostScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        val mockedModule = module {
            factory<TimeLineUseCase> { TimeLineInteractorTest() }
        }

        loadKoinModules(mockedModule)
        composeTestRule.setContent {
            TechTestKozokkuTheme {
                TimeLineScreen()
            }
        }
    }


    @Test
    fun verifyPostScreen() {
        val data = DummyData.postItems.first()
        //verify list
        composeTestRule.onNodeWithTag("PostList").assertIsDisplayed()
        //verify shimmer loading
        composeTestRule.onNodeWithTag("ShimmerGridLoading1").assertIsDisplayed()
        Thread.sleep(1000)
        //verify username
        composeTestRule.onNodeWithText(data.owner?.fullName.orEmpty()).assertIsDisplayed()
        //verify postdate
        composeTestRule.onNodeWithText(data.datePublishing).assertIsDisplayed()
        //verify tag items
        composeTestRule.onNodeWithTag(data.tags.first()).assertIsDisplayed()
        //verify text
        composeTestRule.onNodeWithText(data.text.orEmpty()).assertIsDisplayed()
        //verify total likes
        composeTestRule.onNodeWithText("${data.likes} Likes").assertIsDisplayed()
        //verify LikeButton display and click
        composeTestRule.onNodeWithTag("LikeButton_${data.id}").run {
            assertIsDisplayed()
            performClick()
        }
    }
}