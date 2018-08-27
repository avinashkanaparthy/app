package MigrationExamples_BuildChain

import MigrationExamples_BuildChain.buildTypes.*
import MigrationExamples_BuildChain.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project({
    id("MigrationExamples_BuildChain")
    name = "Build Chain"

    vcsRoot(MigrationExamples_BuildChain_BasicBuildChain)

    buildType(MigrationExamples_BuildChain_First)
    buildType(MigrationExamples_BuildChain_Second)
})
