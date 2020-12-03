pipeline {
    agent any

    tools {
         gradle 'local_gradle'
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