package MigrationExamples_BuildChain.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object MigrationExamples_BuildChain_BasicBuildChain : GitVcsRoot({
    name = "Basic Build Chain"
    url = "https://github.com/antonarhipov/basic-build-chain"
    authMethod = password {
        userName = "antonarhipov"
        password = "credentialsJSON:372bd256-7c6f-4a5b-ac95-a9b4d3505dd2"
    }
})
