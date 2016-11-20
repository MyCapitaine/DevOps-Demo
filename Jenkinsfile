node {
    stage('SCM') {
        git 'https://github.com/MyCapitaine/DevOps-Demo.git'
    }
    stage('QA') {
        sh '/home/hutao/sonar-scanner/bin/sonar-scanner'
    }
    stage('build') {
        def mvnHome = tool 'M3'
        sh "${mvnHome}/bin/mvn -B clean package"
    }
    stage('deploy') {
        sh "docker stop my || true"
        sh "docker rm my || true"
        sh "docker run --name my -p 11111:8080 -d tomcat"
        sh "docker cp target/DevOpsDemo-0.0.1-SNAPSHOT.war my:/usr/local/tomcat/webapps/DevOpsDemo.war"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
