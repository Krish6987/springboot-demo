pipeline{
    agent any
    tools { maven "maven" }
    stages{
     stage ('Build and Package'){
            steps{
                sh "mvn clean install"
            }
        }
    stage('Code Analysis and Coverage- Sonar') 
       {environment {
           scannerHome=tool 'sonarScanner'
       }
            steps {
                withSonarQubeEnv('sonarQube') {
                sh "mvn sonar:sonar"
                }
            }
        }
        stage("Quality Gate") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
              }
         }
        }
        stage ("Upload Artifacts"){
            steps{
                withCredentials([usernamePassword(credentialsId: 'Nexus_Credentials', passwordVariable: 'password', usernameVariable: 'username')]) {
                    sh label: '', script: 'curl -u $username:$password --upload-file target/springboot-demo-0.0.1-SNAPSHOT.war http://159.65.148.159:8081/nexus/content/repositories/srinivas-devops/springboot-0.0.1-SNAPSHOT.war'
                }
            }
        }
        stage("Deployment"){
            steps{
             deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://159.65.155.75:8080')], contextPath: null, war: '**/*.war'
            }
        }
    }
}
