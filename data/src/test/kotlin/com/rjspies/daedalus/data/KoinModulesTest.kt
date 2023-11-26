package com.rjspies.daedalus.data

import com.rjspies.daedalus.data.koin.DataModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.ksp.generated.module
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules
import org.koin.test.verify.verify

class KoinModulesTest : KoinTest {
    @Test
    @OptIn(KoinExperimentalAPI::class)
    fun checkAllModules() {
        checkKoinModules(listOf(DataModule().module))
        DataModule().module.verify()
    }
}
