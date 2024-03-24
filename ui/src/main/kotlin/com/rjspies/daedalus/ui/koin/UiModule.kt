package com.rjspies.daedalus.ui.koin

import com.rjspies.daedalus.domain.koin.DomainModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module([DomainModule::class])
@ComponentScan("com.rjspies.daedalus.ui")
public class UiModule
