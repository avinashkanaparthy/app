package MigrationExamples

import MigrationExamples.buildTypes.*
import MigrationExamples.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project({
    id("MigrationExamples")
    name = "Migration Examples"

    vcsRoot(MigrationExamples_HttpsGithubComAntonarhipovAppRefsHeadsMaster)
    vcsRoot(MigrationExamples_HttpsGithubComAntonarhipovReplacerRefsHeadsMaster)
    vcsRoot(MigrationExamples_HttpsGithubComAntonarhipovDockerHelloWorldRefsHeadsMaster)

    buildType(MigrationExamples_AntScript)
    buildType(MigrationExamples_BuildTest)
    buildType(MigrationExamples_Replacer)
    buildType(MigrationExamples_DockerHelloWorld)
    buildType(MigrationExamples_Controlflow)

    subProject(MigrationExamples_ControlflowChain.Project)
    subProject(MigrationExamples_BuildChain.Project)
})
