package MigrationExamples.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script

object MigrationExamples_DockerHelloWorld : BuildType({
    name = "Docker Hello World"

    vcs {
        root(MigrationExamples.vcsRoots.MigrationExamples_HttpsGithubComAntonarhipovDockerHelloWorldRefsHeadsMaster)
    }

    steps {
        dockerCommand {
            name = "build"
            commandType = build {
                source = path {
                    path = "Dockerfile"
                }
                commandArgs = "--pull -t test"
            }
        }
        script {
            name = "docker run"
            scriptContent = """
                echo "Hello from within the docker container!"
                uname
            """.trimIndent()
            dockerImage = "test:latest"
            dockerRunParameters = "-v /tmp:/tmp -p 8000:8000"
        }
    }
})
