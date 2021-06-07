def remote = [:]
remote.host = "192.168.160.87"
remote.name = "playground"


pipeline {
    agent any
    tools {
        maven 'maven36'
        jdk 'jdk11'
    }
    stages {

        stage ('Build') {
            steps { 
                dir('data-processor'){
                    sh 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Integration tests') {
            steps{    
                dir('data-processor'){
                    script{
                        try {
                            sh "mvn test -Pintegration"
                        } catch(err) {
                            step([$class: 'JUnitResultArchiver', testResults: 
                            '**/target/surefire-reports/TEST-' 
                                + '*IntegrationTest.xml'])
                            throw err
                        }
                    }
                    step([$class: 'JUnitResultArchiver', testResults: 
                        '**/target/surefire-reports/TEST-' 
                            + '*IntegrationTest.xml'])    
                }
            }
        }

        stage ('Maven-Deploy') {
            steps{
                dir('data-processor'){
                    sh 'mvn deploy -f pom.xml -s settings.xml'
                }
            }
        }

        stage('Publish-Image'){
            steps{
                script{
                      docker.withRegistry('http://192.168.160.48:5000') {
                            def dataproc = docker.build("esp31/dataprocessor", "./data-processor")
                            def reactapp = docker.build("esp31/reactapp", "./react-app")
                            // Push the container to the custom Registry 
                            dataproc.push()
                            reactapp.push()
                    }
                }
            }
        }

        stage('Deployment') { 
            steps {
                 withCredentials([usernamePassword(credentialsId: 'esp31_ssh_credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    
                    script {
                      remote.user = USERNAME
                      remote.password = PASSWORD
                      remote.allowAnyHosts = true

                    }
                    
                    sshCommand remote: remote, command: "docker stop esp31_dataprocessor"
                    sshCommand remote: remote, command: "docker rm esp31_dataprocessor"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp31/dataprocessor"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp31/dataprocessor"
                    sshCommand remote: remote, command: "docker create -p 31003:8080 --name esp31_dataprocessor 192.168.160.48:5000/esp31/dataprocessor"
                    sshCommand remote: remote, command: "docker start esp31_dataprocessor"

                    sshCommand remote: remote, command: "docker stop esp31_reactapp"
                    sshCommand remote: remote, command: "docker rm esp31_reactapp"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp31/reactapp"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp31/reactapp"
                    sshCommand remote: remote, command: "docker run -it -d -v /react:/app -v /app/node_modules -p 31004:3000 -e CHOKIDAR_USEPOLLING=true --name esp31_reactapp 192.168.160.48:5000/esp31/reactapp"
                }
                  
                
            }
        }
    }
}
