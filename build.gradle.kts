import io.github.adityabhaskar.dependencygraph.plugin.Direction

plugins {
    alias(libs.plugins.comAndroidApplication) apply false
    alias(libs.plugins.comAndroidLibrary) apply false
    alias(libs.plugins.orgJetbrainsKotlinAndroid) apply false
    alias(libs.plugins.ioGithubAdityahaskarDependencygraph) apply true
}

dependencyGraphConfig {
    graphDirection.set(Direction.TopToBottom)
    repoRootUrl.set("https://github.com/rjspies/Daedalus")
}
