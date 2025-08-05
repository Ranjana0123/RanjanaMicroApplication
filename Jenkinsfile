pipeline {
    agent any

    stages {
        stage('Checkout') {
          steps {
            git branch: 'master', url: 'https://github.com/Ranjana0123/MultiServiceDockerApplication.git'
          }
        }

        stage('Build with Maven') {
            steps {
                bat "${MAVEN_HOME}/bin/mvn clean install -DskipTests"
            }
        }

        stage('Docker Compose - Build') {
            steps {
                bat 'docker-compose build'
            }
        }

        stage('Docker Compose - Up') {
            steps {
                bat 'docker-compose up -d'
            }
        }
    }
}
