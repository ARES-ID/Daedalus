package com.rjspies.daedalus

import com.rjspies.daedalus.data.koin.DataModule
import com.rjspies.daedalus.koin.AppModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinApplication
import org.koin.ksp.generated.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.verify.verify

class KoinModulesTest : KoinTest {
    @Test
    @OptIn(KoinExperimentalAPI::class)
    fun checkAllModules() {
        koinApplication {
            modules(DataModule().module, AppModule().module)
            checkModules()
        }
//        AppModule().module.verify(
//            extraTypes = listOf(
//                androidx.lifecycle.SavedStateHandle::class,
//            ),
//        )
    }
}
