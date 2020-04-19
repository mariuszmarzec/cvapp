package com.marzec.cv.common

import android.content.Context
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StringProviderImplTest {

    private val context = mock<Context>()
    private lateinit var provider: StringProviderImpl
    @BeforeEach
    fun setUp() {
        provider = StringProviderImpl(context)
    }

    @Test
    fun getString() {
        whenever(context.getString(any())) doReturn "test"
        assertEquals(
            "test",
            provider.getString(1)
        )

        verify(context).getString(1)
    }
}