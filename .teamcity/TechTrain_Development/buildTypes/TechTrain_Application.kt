package TechTrain_Development.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*

object TechTrain_Application : BuildType({
    name = "Application"

    dependencies {
        snapshot(TechTrain_Library) {
        }
    }
})
