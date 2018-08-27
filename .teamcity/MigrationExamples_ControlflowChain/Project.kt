package MigrationExamples_ControlflowChain

import MigrationExamples_ControlflowChain.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project({
    id("MigrationExamples_ControlflowChain")
    name = "Controlflow Chain"

    buildType(MigrationExamples_ControlflowChainThird)
    buildType(MigrationExamples_ControlflowChainFirst)
    buildType(MigrationExamples_ControlflowChainSecond)
})
