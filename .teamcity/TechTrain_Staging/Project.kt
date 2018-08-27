package TechTrain_Staging

import TechTrain_Staging.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project({
    id("TechTrain_Staging")
    name = "Staging"

    buildType(TechTrain_Docker)
    buildType(TechTrain_TestApplication)
    buildTypesOrder = arrayListOf(TechTrain_Docker, TechTrain_TestApplication)
})
