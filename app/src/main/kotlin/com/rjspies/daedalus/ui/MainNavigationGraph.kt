package com.rjspies.daedalus.ui

import com.ramcosta.composedestinations.annotation.NavGraph

@NavGraph(default = true)
annotation class MainNavigationGraph(
    val start: Boolean = false,
)
