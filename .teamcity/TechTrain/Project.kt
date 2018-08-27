package TechTrain

import TechTrain.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project({
    id("TechTrain")
    name = "TechTrain"

    vcsRoot(TechTrain_HttpsGithubComAntonarhipovCommonRefsHeadsMaster)
    vcsRoot(TechTrain_Docker_1)
    subProjectsOrder = arrayListOf(RelativeId("TechTrain_Development"), RelativeId("TechTrain_Staging"), RelativeId("TechTrain_Live"))

    subProject(TechTrain_Live.Project)
    subProject(TechTrain_Development.Project)
    subProject(TechTrain_Staging.Project)
})
