pipeline {
    agent any

    stages {
        stage("Kotlinter") {
            steps {
                sh "./gradlew lintKotlin"
            }
        }
        stage("Detekt") {
            steps {
                sh "./gradlew detekt"
            }
        }
    }
}
