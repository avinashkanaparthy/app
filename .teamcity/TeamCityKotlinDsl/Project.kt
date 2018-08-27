package TeamCityKotlinDsl

import TeamCityKotlinDsl.buildTypes.*
import TeamCityKotlinDsl.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project({
    id("TeamCityKotlinDsl")
    name = "TeamCity Kotlin DSL"

    vcsRoot(TeamCityKotlinDsl_HttpsGithubComAntonarhipovTeamcityKotlinDslRefsHeadsMaster)

    buildType(TeamCityKotlinDsl_Hugo)
})
