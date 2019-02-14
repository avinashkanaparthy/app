pipeline {
  agent any
  stages {
    stage('Init') {
      agent {
        docker {
          image 'gradle:5.2.1-jdk8'
        }

      }
      steps {
        sh './gradlew clean build'
      }
    }
    stage('Finalize') {
      steps {
        archiveArtifacts(artifacts: 'build/libs/*.jar', allowEmptyArchive: true, fingerprint: true)
      }
    }
  }
}