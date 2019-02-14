pipeline {
  agent {
     docker {
       image 'gradle:5.2.1-jdk8'
     }
  }
  stages {
    stage('Init') {
      steps {
        echo 'Hello'
        sh './gradlew clean build'
      }
    }
  }
  post {
      always {
        archiveArtifacts(artifacts: 'build/libs/*.jar', allowEmptyArchive: true, fingerprint: true)
      }
    }
}
