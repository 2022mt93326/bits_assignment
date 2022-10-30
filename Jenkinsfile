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
                    
                 $class: 'S3BucketPublisher',
        entries: [[
            sourceFile: 'mybinaryFile',
            bucket: 'GoBinaries',
            selectedRegion: 'eu-west-1',
            noUploadOnFailure: true,
            managedArtifacts: true,
            flatten: true,
            showDirectlyInBrowser: true,
            keepForever: true,
        ]],
        profileName: 'myprofile',
        dontWaitForConcurrentBuildCompletion: false, 
    ])
                      
            }
            
        }
    }
}
