package TechTrain_Development.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*

object TechTrain_TestExt : BuildType({
    name = "TestExt"

    dependencies {
        snapshot(TechTrain_Application) {
        }
    }
})