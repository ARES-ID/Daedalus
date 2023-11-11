fun generateVersionCode(offset: Int = 0): Int {
    val standardOutput = java.io.ByteArrayOutputStream()
    project.exec {
        commandLine("git", "rev-list", "--count", "HEAD")
        this.standardOutput = standardOutput
    }
    val commitCount = standardOutput.toString().trim().toInt()
    val versionCode = commitCount + offset
    project.logger.debug("Generating version code = $versionCode")
    return versionCode
}
