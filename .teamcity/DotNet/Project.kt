package DotNet

import DotNet.buildTypes.*
import DotNet.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project
import jetbrains.buildServer.configs.kotlin.v2018_1.projectFeatures.nuGetFeed

object Project : Project({
    id("DotNet")
    name = "DotNet"

    vcsRoot(DotNet_HttpsGithubComAntonarhipovDotnetcliRefsHeadsMaster)
    vcsRoot(DotNet_HttpsGithubComAntonarhipovAppLoggerNugetSampleRefsHeadsMaster)

    buildType(DotNet_AppLoggerNugetSample)

    template(DotNet_SomeTemplate)

    features {
        nuGetFeed {
            id = "repository-nuget-default"
            name = "default"
            param("indexPackages", "false")
        }
    }
})
