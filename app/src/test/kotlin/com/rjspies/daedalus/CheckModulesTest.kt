package com.rjspies.daedalus

import androidx.lifecycle.SavedStateHandle
import com.rjspies.daedalus.koin.appModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class CheckModulesTest : KoinTest {
    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkAllModules() {
        appModule.verify(
            extraTypes = listOf(SavedStateHandle::class),
        )
    }
}
