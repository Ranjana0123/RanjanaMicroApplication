pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'jenkins-maven' // Set this in Jenkins tools config
        DOCKER_IMAGE = 'docker-image'
        CONTAINER_NAME = 'docker-container'
    }

    stages {
        stage('Checkout') {
          steps {
            git branch: 'main', url: 'https://github.com/Ranjana0123/RanjanaMicroApplication.git'
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
          bat 'docker rm -f department-service-container || exit 0'
                bat 'docker rm -f user-service-container || exit 0'
                bat 'docker-compose down'
                bat 'docker-compose up -d --build'
                }
        }
    }
}
