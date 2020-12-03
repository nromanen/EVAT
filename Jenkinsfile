pipeline {
    agent any

    tools {
         gradle "gradle-6.7.1"
    }

    stages {
        stage('Build and test EventMenuPage') {
            steps {
                sh "gradle test --tats EventMenuTest"
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