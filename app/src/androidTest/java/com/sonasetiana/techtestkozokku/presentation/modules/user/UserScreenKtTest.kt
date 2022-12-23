package com.sonasetiana.techtestkozokku.presentation.modules.user

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.compose.collectAsLazyPagingItems
import com.sonasetiana.techtestkozokku.data.DummyData
import com.sonasetiana.techtestkozokku.presentation.theme.TechTestKozokkuTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            TechTestKozokkuTheme {
                val dummyUser = DummyData.dataListUser().collectAsLazyPagingItems()
                UserListView(items = dummyUser, isRefreshing = false)
            }
        }
    }

    @Test
    fun check_user_card_are_displayed() {
        val data = DummyData.userItems.first()
        composeTestRule.onNodeWithText(data.fullName).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(data.picture.orEmpty().split("/").last()).assertIsDisplayed()
    }
}