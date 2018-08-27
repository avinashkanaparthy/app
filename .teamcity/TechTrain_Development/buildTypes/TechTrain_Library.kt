package TechTrain_Development.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*

object TechTrain_Library : BuildType({
    name = "Library"

    vcs {
        root(TechTrain.vcsRoots.TechTrain_HttpsGithubComAntonarhipovCommonRefsHeadsMaster)
    }
})
