package com.rjspies.daedalus.koin

import com.rjspies.daedalus.data.koin.DataModule
import com.rjspies.daedalus.domain.koin.DomainModule
import com.rjspies.daedalus.ui.koin.UiModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module([UiModule::class, DomainModule::class, DataModule::class])
@ComponentScan("com.rjspies.daedalus")
class AppModule
