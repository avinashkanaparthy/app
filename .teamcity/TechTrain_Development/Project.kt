package TechTrain_Development

import TechTrain_Development.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project({
    id("TechTrain_Development")
    name = "Development"

    buildType(TechTrain_TestUI)
    buildType(TechTrain_TestReport)
    buildType(TechTrain_Application)
    buildType(TechTrain_TestExt)
    buildType(TechTrain_TestOps)
    buildType(TechTrain_Library)
    buildTypesOrder = arrayListOf(TechTrain_Library, TechTrain_Application, TechTrain_TestUI, TechTrain_TestExt, TechTrain_TestOps, TechTrain_TestReport)
})
