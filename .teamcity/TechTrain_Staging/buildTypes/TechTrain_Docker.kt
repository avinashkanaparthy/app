package TechTrain_Staging.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*

object TechTrain_Docker : BuildType({
    name = "Docker"

    vcs {
        root(TechTrain.vcsRoots.TechTrain_Docker_1)
    }

    dependencies {
        snapshot(TechTrain_Development.buildTypes.TechTrain_TestReport) {
            reuseBuilds = ReuseBuilds.ANY
        }
    }
})
