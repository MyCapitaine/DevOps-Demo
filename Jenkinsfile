node {
    stage('SCM') {
        git 'https://github.com/MyCapitaine/DevOps-Demo.git'
    }
    stage('deploy') {
        sh "sudo docker stop my || true"
        sh "sudo docker rm my || true"
        sh "sudo docker run --name my -p 11111:8080 -d tomcat"
        sh "sudo docker cp DevOpsDemo.war my:/usr/local/tomcat/webapps"
    }
}
