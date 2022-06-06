pipeline {
    agent none

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "apache-maven-3.8.1"
    }

    stages {
        stage('Run software on slave 1') {
            agent { label 'slave1-build' }
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/mohammed0yassin/cicd-jenkins/tree/master/slave1-files'

                // Run Maven on a Unix agent.
                sh "mvn spring-boot:run"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Run software on slave 2') {
            agent { label 'slave2-build' }
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/mohammed0yassin/cicd-jenkins/tree/master/slave2-files'

                // Run Maven on a Unix agent.
                sh "mvn spring-boot:run"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
    }
}
