package com.sonasetiana.techtestkozokku.utils

import org.junit.Assert
import org.junit.Test

class DateExtTest {
    @Test
    fun `convert ISO 8601 fail format then should be null`() {
        val dateTime = "2020-05-03 08:21:17"
        Assert.assertNull(DateExt.convertDate(dateTime))
    }

    @Test
    fun `convert ISO 8601 format then should not null`() {
        val dateTime = "2020-05-03T08:21:17.580Z"
        Assert.assertNotNull(DateExt.convertDate(dateTime))
    }

    @Test
    fun `convert ISO 8601 format then should be return timestamp`() {
        val dateTime = "2020-05-03T08:21:17.580Z"
        Assert.assertEquals(DateExt.convertDate(dateTime)?.time, 1588468877580)
    }

    @Test
    fun `parse ISO 8601 format then should be empty`() {
        val dateTime = "2020-05-03 08:21:17"
        Assert.assertEquals(DateExt.parseDate(dateTime), "")
    }

    @Test
    fun `parse ISO 8601 format then should be not empty`() {
        val dateTime = "2020-05-03T08:21:17.580Z"
        Assert.assertEquals(DateExt.parseDate(dateTime), "03 May 2020 08:21:17")
    }
}