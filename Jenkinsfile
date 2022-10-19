pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6'
    }

    stages {
        stage('Build') {
            steps {
                bat "cd ems"
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
    }
}
