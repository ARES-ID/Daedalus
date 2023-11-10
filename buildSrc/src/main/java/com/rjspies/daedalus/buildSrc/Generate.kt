package com.rjspies.daedalus.buildSrc

import org.gradle.api.Project
import sun.jvmstat.monitor.MonitoredVmUtil.commandLine
import java.io.ByteArrayOutputStream

fun generateVersionCode(project: Project): Int {
    val standardOutput = ByteArrayOutputStream()
    project.exec {
        commandLine("git", "rev-list", "--count", "HEAD")
        this.standardOutput = standardOutput
    }
    val commitCount = standardOutput.toString().trim().toInt()
    val offset = libs.versions.versionCodeOffset.get().toInt()
    val versionCode = commitCount + offset
    project.logger.debug("Generating version code = $versionCode")
    return versionCode
}
