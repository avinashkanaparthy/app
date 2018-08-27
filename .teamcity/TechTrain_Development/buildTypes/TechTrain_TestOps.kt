package TechTrain_Development.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*

object TechTrain_TestOps : BuildType({
    name = "TestOps"

    dependencies {
        snapshot(TechTrain_Application) {
        }
    }
})
