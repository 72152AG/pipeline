node {
    def WORKSPACE = "/var/lib/jenkins/workspace"
    def dockerImageTag = "springboot-deploy${env.BUILD_NUMBER}"
try{
    notifyBuild('STARTED')
    stage('Clone Repo') {
        // for display purposes
        // Get some code from a GitHub repository
        git url: 'https://github.com/dadzio008/HomeAutomaticationAccountsManagement.git',
            credentialsId: '8dc1d2e4-1e36-46d6-b5d5-e40ef097a391',
            branch: 'develop'
    }
    stage('Build docker'){
         dockerImage = docker.build("springboot-deploy:${env.BUILD_NUMBER}")
    }

    stage('Deploy docker'){
         echo "Docker Image Tag Name: ${dockerImageTag}"
         sh "docker stop springboot-deploy || true && docker rm springboot-deploy || true"
         sh "docker run --name springboot-deploy -d -p 8081:8081 springboot-deploy:${env.BUILD_NUMBER}"
         sh "sudo docker logs springboot-deploy"
    }
//     stage('Deploy database on Docker'){
//         sh 'mvn clean compile'
//         sh 'mvn clean install -Dmaven.test.skip=true spring-boot:repackage'
//         sh 'sudo docker compose build'
//         sh 'docker compose down'
//         sh 'docker compose up -d'
//     }
    stage ("Wait for testing")
    {
       sh 'sleep 30'
    }
    stage('Health check'){
        def healthUrl = "http://127.0.0.1:8081/healthcheck"

                            def response = sh(
                                script: "curl -m 10 ${healthUrl}",
                                returnStdout: true
                            ).trim()

                            // Check the HTTP status code
                            echo response
                            if (response == '"OK"') {
                                echo "Application is healthy"
                                echo response
                            } else {
                                error "Application health check failed with status code: $response"
                            }
    }

}catch(e){
//     if (e == 403){
//     currentBuild.result = "SUCCESS"} else {
        currentBuild.result = "FAILED"
        throw e
//     }
}finally{
    notifyBuild(currentBuild.result)
 }
}


def notifyBuild(String buildStatus = 'STARTED'){

  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def now = new Date()

  // message
  def subject = "${buildStatus}, Job: ${env.JOB_NAME} FRONTEND - Deployment Sequence: [${env.BUILD_NUMBER}] "
  def summary = "${subject} - Check On: (${env.BUILD_URL}) - Time: ${now}"
  def subject_email = "Spring boot Deployment"
  def details = """<p>${buildStatus} JOB </p>
    <p>Job: ${env.JOB_NAME} - Deployment Sequence: [${env.BUILD_NUMBER}] - Time: ${now}</p>
    <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME}</a>"</p>"""

}