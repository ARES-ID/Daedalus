package com.rjspies.daedalus.buildSrc

data object Version {
    const val kotlin = "1.9.20"
}

data object Plugin {
    val kotlinGradle by lazy {
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    }
}
