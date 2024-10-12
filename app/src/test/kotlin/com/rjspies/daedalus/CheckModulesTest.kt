package com.rjspies.daedalus

import com.rjspies.daedalus.koin.appModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.definition
import org.koin.test.verify.injectedParameters
import org.koin.test.verify.verify

class CheckModulesTest : KoinTest {
    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkAllModules() {
        appModule.verify(
            injections = injectedParameters(
                definition<com.rjspies.daedalus.ui.MainViewModel>(androidx.lifecycle.SavedStateHandle::class),
                definition<com.rjspies.daedalus.ui.SettingsViewModel>(androidx.lifecycle.SavedStateHandle::class),
                definition<com.rjspies.daedalus.ui.InsertWeightViewModel>(androidx.lifecycle.SavedStateHandle::class),
            ),
        )
    }
}
