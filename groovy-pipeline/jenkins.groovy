pipeline {
    agent none

    tools {
        maven "apache-maven-3.8.1"
    }

    stages {
        stage('Run software on slave 1') {
            agent { label 'slave1-build' }
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/mohammed0yassin/cicd-jenkins-slave1'

                // Run Maven on a Unix agent.
                sh "mvn compile"
                sh "nohup mvn spring-boot:run &"

            }
        }
        stage('Run software on slave 2') {
            agent { label 'slave2-build' }
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/mohammed0yassin/cicd-jenkins-slave2'

                // Run Maven on a Unix agent.
                sh "mvn compile"
                sh "nohup mvn spring-boot:run &"

            }
        }
    }
}
