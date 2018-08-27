package MigrationExamples.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object MigrationExamples_HttpsGithubComAntonarhipovReplacerRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/antonarhipov/replacer#refs/heads/master"
    url = "https://github.com/antonarhipov/replacer"
    authMethod = password {
        userName = "antonarhipov"
        password = "credentialsJSON:d20390da-57d4-4cbe-904e-95eb93e91020"
    }
})
