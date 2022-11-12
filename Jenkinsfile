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
                expression {env.GIT_BRANCH == '1main'}
            }  
            steps {
                s3Upload(consoleLogLevel: 'INFO', dontSetBuildResultOnFailure: false, dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: 'ems-artifact', excludedFile: '', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: false, noUploadOnFailure: false, selectedRegion: 'ap-northeast-1', showDirectlyInBrowser: false, sourceFile: 'ems/target/ems-0.0.1-SNAPSHOT.jar', storageClass: 'STANDARD', uploadFromSlave: false, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'ems-artifact', userMetadata: [])
            
            } 
            
        }
         stage("Deploy"){
            when { 
                expression {env.GIT_BRANCH == 'main'}
            } 
            steps {  
                   sshagent(['ssh-key']) {
                       sh'''#!/bin/bash
                            ssh -o StrictHostKeyChecking=no -tt ec2-user@3.115.10.230 << 'EOF'
                            aws s3 cp s3://ems-artifact/ems-0.0.1-SNAPSHOT.jar ems-0.0.1-SNAPSHOT.jar
                            xargs kill <pid.txt
                            java -jar ems-0.0.1-SNAPSHOT.jar & > pid.txt
                            exit
                            EOF
                       '''
                  }    
       
            } 
            
        }
    }
}
