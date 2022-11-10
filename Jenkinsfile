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
            when {  
                expression {env.GIT_BRANCH == 'main'}
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
                sshagent { 
                   sh "ssh -o StrictHostKeyChecking=no ec2-user@18.181.82.198"
                }
            } 
            
        }
    }
}
