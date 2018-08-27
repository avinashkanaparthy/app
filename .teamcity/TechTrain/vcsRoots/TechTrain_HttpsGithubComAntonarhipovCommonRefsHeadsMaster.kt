package TechTrain.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object TechTrain_HttpsGithubComAntonarhipovCommonRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/antonarhipov/common#refs/heads/master"
    url = "https://github.com/antonarhipov/common"
    authMethod = password {
        userName = "antonarhipov"
        password = "credentialsJSON:22dd1f8f-17b2-46af-bc46-38ef2f6955d3"
    }
})
