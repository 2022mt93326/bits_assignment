pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6'
    }

    stages {
        stage('Build') {
            steps {
                bat """
                    cd ems
                    mvn clean package
                """
            }
        }
        stage('Test') {
            steps {
                bat """
                    cd ems
                    mvn test
                """
            }
        }
        stage("Upload"){
            steps{
                    
                s3Upload(entries[{ 
                            bucket: 'ems-artifact',
                            sourceFile: 'ems-0.0.1-SNAPSHOT.jar',
                            path: 'ems-0.0.1-SNAPSHOT.jar'
                    ])
                      
            }
            
        }
    }
}
