package MigrationExamples.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object MigrationExamples_HttpsGithubComAntonarhipovDockerHelloWorldRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/antonarhipov/docker-hello-world#refs/heads/master"
    url = "https://github.com/antonarhipov/docker-hello-world"
    authMethod = password {
        userName = "antonarhipov"
        password = "credentialsJSON:d20390da-57d4-4cbe-904e-95eb93e91020"
    }
})
