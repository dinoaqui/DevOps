pipeline {
    agent any

    environment {
		registry = "devopscloudweek2/devopsapp"
        registryCredential = "dockerhub_id"
        dockerImage = ''
    }

    stages {
    	stage('Clone Repository') {
    		steps {  
                git url: 'https://gitlab.com/devopscloudweek1/devops-app.git'
			}
    	}
    	stage('Build Docker Image') {
            steps{
                script {
                    dockerImage = docker.build registry + ":develop"
                }
            }
        }
    	stage('Send image to Docker Hub') {
            steps{
                script {
                    docker.withRegistry( '', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }
    	stage('Deploy') {
		    steps{
                step([$class: 'AWSCodeDeployPublisher',
                    applicationName: 'app01-application',
                    awsAccessKey: "AKIAZTOKAUMQYNQ37WP5",
                    awsSecretKey: "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",
                    credentials: 'awsAccessKey',
                    deploymentGroupAppspec: false,
                    deploymentGroupName: 'service',
                    deploymentMethod: 'deploy',
                    excludes: '',
                    iamRoleArn: '',
                    includes: '**',
                    pollingFreqSec: 15,
                    pollingTimeoutSec: 600,
                    proxyHost: '',
                    proxyPort: 0,
                    region: 'us-east-2',
                    s3bucket: 'devopscloudweek2-app01', 
                    s3prefix: '', 
                    subdirectory: '',
                    versionFileName: '',
                    waitForCompletion: true])
            }
        }
    	stage('Cleaning up') {
        	steps {
            	sh "docker rmi $registry:develop"
        	}
		}
    }
}