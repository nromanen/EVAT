pipeline {
    agent any



    stages {
        stage('Build and test EventMenuPage') {
            steps {
                sh "./gradlew test --tats EventMenuTest"
            }
        }
        stage('reports') {
            steps {
            script {
                    allure([
                            includeProperties: false,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'target/allure-results']]
                    ])
            }
            }
        }
    }
}