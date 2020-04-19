package com.marzec.cv.ui.home

import com.marzec.cv.stubCv
import com.marzec.cv.stubHeaderModel
import com.marzec.cv.views.model.UrlImage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class HomeScreenMapperImplTest {

    private lateinit var mapper: HomeScreenMapperImpl

    @BeforeEach
    fun setUp() {
        mapper = HomeScreenMapperImpl()
    }

    @Test
    fun `mapItems | create header`() {
        val cv = stubCv(
            imageUrl = "image_url",
            firstName = "first",
            lastName = "last",
            phone = "phone",
            email = "email"
        )
        val header = mapper.mapItems(cv).header

        assertEquals(
            stubHeaderModel(
                image = UrlImage(url = "image_url"),
                fulName = "first last",
                email = "email",
                phoneNumber = "phone"
            ),
            header
        )
    }
}