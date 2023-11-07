pipeline {
    agent any
    parameters {
        string(name: 'REPO_URL', defaultValue: 'https://github.com/rjspies/Daedalus.git', description: '')
        string(name: 'BRANCH', defaultValue: 'Jenkins-test', description: '')
        string(name: 'JENKINSFILE_PATH', defaultValue: '.jenkins/build.groovy', description: '')
    }
    stages {
        stage("Checkout"){
            steps {
                git branch: "${params.BRANCH}", url: "${params.REPO_URL}"
            }
        }
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
