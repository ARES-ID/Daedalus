package com.rjspies.daedalus.koin

import com.rjspies.daedalus.ui.koin.UiModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module([UiModule::class])
@ComponentScan("com.rjspies.daedalus")
class AppModule
