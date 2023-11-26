package com.rjspies.daedalus.koin

import com.rjspies.daedalus.data.koin.DataModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module([DataModule::class])
@ComponentScan("com.rjspies.daedalus")
class AppModule
