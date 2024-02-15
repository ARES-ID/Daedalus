package com.rjspies.daedalus.domain.koin

import com.rjspies.daedalus.data.koin.DataModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module([DataModule::class])
@ComponentScan("com.rjspies.daedalus.domain")
public class DomainModule
