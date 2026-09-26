pipeline {
    agent any

    tools {
        maven 'M3'   // name must match what you configured in Manage Jenkins > Tools
        jdk 'JDK_21'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Shash31002/java_dbcv_framework.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'   // publishes TestNG/JUnit results in Jenkins UI
        publishHTML(target: [
            reportDir: 'test-output/ExtentReport',   // adjust to your actual output path
            reportFiles: 'index.html',                // or whatever your report's entry file is named
            reportName: 'Extent Report'
        ])
        }
    }
}