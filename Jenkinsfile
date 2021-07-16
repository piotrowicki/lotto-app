pipeline {
    agent any
    options {
        timeout(time: 20, unit: 'MINUTES') 
    }
    tools {
        maven 'Maven 3.6.3'
    }
    environment {
        IMAGE_NAME = 'lotto-app-image'
    }
    stages {
        stage('Preparation') {
            steps {
                git branch: 'master', url: 'https://github.com/piotrowicki/lotto-app.git'
            }
        }
        stage('Build application') {
            steps {
                sh 'mvn clean compile package'
            }
        }
        stage('Build image') {
            steps {
                sh 'docker build -f src/main/docker/Dockerfile.jvm -t ${IMAGE_NAME} .'
            }
        }
    }
}
