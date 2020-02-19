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
                nexusArtifactUploader artifacts: [[artifactId: 'spring-boot-starter-parent', classifier: '', file: '/var/lib/jenkins/workspace/Test-Spring/target/springboot-0.0.1-SNAPSHOT.war', type: 'war']], credentialsId: 'Nexus_Credentials', groupId: 'org.springframework.boot', nexusUrl: '159.65.148.159:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'srinivas-devops', version: '1.1'
                
            }
        }
        stage("Deployment"){
            steps{
             deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://159.65.155.75:8080')], contextPath: null, war: '**/*.war'
            }
        }
    }
}
