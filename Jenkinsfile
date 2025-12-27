pipeline {
  agent any

  environment {
    PROJECT_ID = 'avian-silo-477711-h9'
    REGION     = 'us-central1'
    REPO       = 'soap-images'
    IMAGE      = 'soap-order-service'
    TAG        = "${env.BUILD_NUMBER}"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build JAR') {
      steps {
        sh 'mvn -B clean package -DskipTests'
      }
    }

    stage('Auth GCP') {
      steps {
        sh '''
          gcloud auth list
          gcloud config set project $PROJECT_ID
          gcloud auth configure-docker ${REGION}-docker.pkg.dev --quiet
        '''
      }
    }

    stage('Docker Build') {
      steps {
        sh '''
          docker build -t ${REGION}-docker.pkg.dev/${PROJECT_ID}/${REPO}/${IMAGE}:${TAG} .
        '''
      }
    }

    stage('Docker Push') {
      steps {
        sh '''
          docker push ${REGION}-docker.pkg.dev/${PROJECT_ID}/${REPO}/${IMAGE}:${TAG}
        '''
      }
    }
  }

  post {
    success {
      echo "Image pushed successfully"
    }
  }
}
