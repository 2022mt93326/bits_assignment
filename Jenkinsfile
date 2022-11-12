pipeline {
    agent any
    
     environment {
        SSH_KEY = credentials('ssh-key')
    }

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
            when {  
                expression {env.GIT_BRANCH == 'main1'}
            }  
            steps {
                s3Upload(consoleLogLevel: 'INFO', dontSetBuildResultOnFailure: false, dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: 'ems-artifact', excludedFile: '', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: false, noUploadOnFailure: false, selectedRegion: 'ap-northeast-1', showDirectlyInBrowser: false, sourceFile: 'ems/target/ems-0.0.1-SNAPSHOT.jar', storageClass: 'STANDARD', uploadFromSlave: false, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'ems-artifact', userMetadata: [])
            
            } 
            
        }
         stage("Deploy to staging"){
            when { 
                expression {env.GIT_BRANCH == 'main'}
            } 
            steps {  
               sshagent(['ssh-key']) {
                   sh'''#!/bin/bash
                        ssh -o StrictHostKeyChecking=no -tt ec2-user@3.115.10.231 << 'EOF'
                        aws s3 cp s3://ems-artifact/ems-0.0.1-SNAPSHOT.jar ems-0.0.1-SNAPSHOT.jar
                        xargs kill <pid.txt
                        java -jar ems-0.0.1-SNAPSHOT.jar &
                        echo $! > pid.txt
                        exit
                        EOF
                   '''
              }    
            }  
        }
    }
    post {
        failure {
            mail to: '2022MT93326@wilp.bits-pilani.ac.in', 
                subject: 'Pipeline failed for ${currentBuild.fullDisplayName}'
                body: "Broken build : ${env.BUILD_URL}"
        }
    }
}
