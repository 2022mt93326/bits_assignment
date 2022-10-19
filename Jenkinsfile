pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6'
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/2022mt93326/bits_assignment.git'
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
    }
}
