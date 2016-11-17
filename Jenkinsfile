node {
    stage('SCM') {
        git 'https://github.com/MyCapitaine/DevOps-Demo.git'
    }
    stage('deploy') {
        sh "docker stop my || true"
        sh "docker rm my || true"
        sh "docker run --name my -p 11111:8080 -d tomcat"
        sh "docker cp DevOpsDemo.war my:/usr/local/tomcat/webapps"
    }
}
