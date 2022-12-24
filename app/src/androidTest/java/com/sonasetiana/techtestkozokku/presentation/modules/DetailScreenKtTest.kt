package com.sonasetiana.techtestkozokku.presentation.modules

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.sonasetiana.techtestkozokku.data.DummyData
import com.sonasetiana.techtestkozokku.domain.DetailInteractorTest
import com.sonasetiana.techtestkozokku.domain.modules.detail.DetailUseCase
import com.sonasetiana.techtestkozokku.presentation.modules.detail.DetailScreen
import com.sonasetiana.techtestkozokku.presentation.theme.TechTestKozokkuTheme
import com.sonasetiana.techtestkozokku.utils.upFirst
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class DetailScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        val mockedModule = module {
            factory<DetailUseCase> { DetailInteractorTest() }
        }

        loadKoinModules(mockedModule)
        composeTestRule.setContent {
            TechTestKozokkuTheme {
                DetailScreen(userId = DummyData.dataUserDetail.id.orEmpty())
            }
        }
    }

    @Test
    fun verifyDetailScreen() {
        val data = DummyData.dataUserDetail
        val post = DummyData.userPostItems.first()
        //verify loading
        composeTestRule.onNodeWithTag("DetailLoading").assertIsDisplayed()
        Thread.sleep(1000)
        //verify BackButton
        composeTestRule.onNodeWithTag("BackButton").assertIsDisplayed()
        //verify SearchField
        composeTestRule.onNodeWithTag("SearchField").assertIsDisplayed()
        //verify name
        composeTestRule.onNodeWithText(data.fullName).assertIsDisplayed()
        //verify label gender
        composeTestRule.onNodeWithText("Gender :").assertIsDisplayed()
        //verify value gender
        composeTestRule.onNodeWithText(data.gender.upFirst()).assertIsDisplayed()
        //verify label Date of birth
        composeTestRule.onNodeWithText("Date of birth :").assertIsDisplayed()
        //verify value Date of birth
        composeTestRule.onNodeWithText(data.dateOfBirthFormatted).assertIsDisplayed()
        //verify label Join from
        composeTestRule.onNodeWithText("Join from :").assertIsDisplayed()
        //verify value Join from
        composeTestRule.onNodeWithText(data.joinDate).assertIsDisplayed()
        //verify label email
        composeTestRule.onNodeWithText("Email :").assertIsDisplayed()
        //verify value email
        composeTestRule.onNodeWithText(data.email.orEmpty()).assertIsDisplayed()
        //verify label address
        composeTestRule.onNodeWithText("Address :").assertIsDisplayed()
        //verify value address
        composeTestRule.onNodeWithText(data.address).assertIsDisplayed()
        //verify AddUserButton
        composeTestRule.onNodeWithTag("AddUserButton").run {
            assertIsDisplayed()
            performClick()
        }
        //verify PostUserList
        composeTestRule.onNodeWithTag("PostUserList").assertIsDisplayed()
        //verify ShimmerGridLoading
        composeTestRule.onNodeWithTag("ShimmerGridLoading1").assertIsDisplayed()
        Thread.sleep(1000)
        composeTestRule.onNodeWithTag("PostUserList").performScrollToIndex(1)
        //verify post postdate
        composeTestRule.onNodeWithText(post.datePublishing).assertIsDisplayed()
        //verify post tag items
        composeTestRule.onNodeWithTag(post.tags.first()).assertIsDisplayed()
        //verify post text
        composeTestRule.onNodeWithText(post.text.orEmpty()).assertIsDisplayed()
        //verify post total likes
        composeTestRule.onNodeWithText("${post.likes} Likes").assertIsDisplayed()
        //verify post LikeButton display and click
        composeTestRule.onNodeWithTag("LikeButton_${post.id}").run {
            assertIsDisplayed()
            performClick()
        }
    }
}