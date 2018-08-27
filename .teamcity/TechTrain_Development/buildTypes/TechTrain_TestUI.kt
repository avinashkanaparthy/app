package TechTrain_Development.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*

object TechTrain_TestUI : BuildType({
    name = "TestUI"

    dependencies {
        snapshot(TechTrain_Application) {
        }
    }
})
