package TechTrain.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object TechTrain_Docker_1 : GitVcsRoot({
    id("TechTrain_Docker")
    name = "Docker"
    url = "https://github.com/antonarhipov/nanoservice"
    authMethod = password {
        userName = "antonarhipov"
    }
})
