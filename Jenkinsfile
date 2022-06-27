pipeline {
    agent any

    environment {
        GIT_PATH = "https://github.com/asemin08/ms-test.git"
        GIT_BRANCH = "main"
    }

    stages {
        stage('Clean workspace'){
            steps{
            	cleanWs()
            }
        }
        stage('récupération du code source et récupération de la bonne branch') {
            steps {
                checkout([$class: 'GitSCM',
                    branches: [[name: "*/${GIT_BRANCH}"]],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    submoduleCfg: [],
                    userRemoteConfigs: [[
                        url: "${GIT_PATH}"
                    ]]
                ])
            }
        }

        stage('Compile du projet') {
            steps {
                sh("mvn clean compile")
            }
        }

        stage('packaging du projet') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh("mvn package sonar:sonar")
                }
            }
        }

        stage("Quality gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }

        stage('Maven nexus archive') {
            steps {
                sh("mvn deploy -DskipTests")
            }
        }

        stage('lancement du build  pour créer l\'image') {
            steps {
                build job: 'BuildJavaImage', parameters: [string(value: 'openjdk', name: 'image'), string(value: '17-jdk-slim', name: 'tag'), string(value: 'verkeur08/ms-api', name: 'newImage'), string(value: 'eu.ensup', name: 'groupId'), string(value: 'microservice-test', name: 'artifactId')]
            }
        }

    }
}
