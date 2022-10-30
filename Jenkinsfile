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
                    //withAWS(region:"${region}", credentials:"${aws_credential}){
                        s3Upload(file:"ems-0.0.1-SNAPSHOT.jar", bucket:"ems-artifact", path:"ems")
                    //}    
            }
            /*post {
                    success{
                        office365ConnectorSend message: "${notify_text}<br>commit id: ${commitId}", status:"Success Upload", webhookUrl:"${webHook_url}"
                    }
                    failure{
                        office365ConnectorSend message: "Fail build,<br> see (<${env.BUILD_URL}>)", status:"Fail Upload", webhookUrl:"${webHook_url}"
                    }
            }*/
        }
    }
}
