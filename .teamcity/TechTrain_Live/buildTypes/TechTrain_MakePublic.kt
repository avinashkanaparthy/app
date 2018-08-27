package TechTrain_Live.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*

object TechTrain_MakePublic : BuildType({
    name = "MakePublic"

    enablePersonalBuilds = false
    type = BuildTypeSettings.Type.DEPLOYMENT
    maxRunningBuilds = 1

    dependencies {
        snapshot(TechTrain_Staging.buildTypes.TechTrain_TestApplication) {
        }
    }
})
