node {
    stage('SCM') {
        git 'https://github.com/MyCapitaine/DevOps-Demo.git'
    }
    stage('QA') {
        sh '/home/hutao/sonar-scanner/bin/sonar-scanner'
    }
    stage('deploy') {
        sh "docker stop my || true"
        sh "docker rm my || true"
        sh "docker run --name my -p 11111:8080 -d tomcat"
        sh "docker cp DevOpsDemo.war my:/usr/local/tomcat/webapps"
    }
}
