import io.github.adityabhaskar.dependencygraph.plugin.Direction

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.dependencyGraph) apply true
}

dependencyGraphConfig {
    graphDirection.set(Direction.TopToBottom)
    repoRootUrl.set("https://github.com/rjspies/Daedalus")
}
