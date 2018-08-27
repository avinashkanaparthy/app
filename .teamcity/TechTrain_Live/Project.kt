package TechTrain_Live

import TechTrain_Live.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project({
    id("TechTrain_Live")
    name = "Live"

    buildType(TechTrain_MakePublic)
    buildTypesOrder = arrayListOf(TechTrain_MakePublic)
})
