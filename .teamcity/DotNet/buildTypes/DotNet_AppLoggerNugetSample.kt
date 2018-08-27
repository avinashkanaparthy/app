package DotNet.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.nuGetPackagesIndexer
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object DotNet_AppLoggerNugetSample : BuildType({
    name = "AppLoggerNugetSample"

    artifactRules = "bin/Debug/AppLogger.1.0.0.nupkg"

    vcs {
        root(DotNet.vcsRoots.DotNet_HttpsGithubComAntonarhipovAppLoggerNugetSampleRefsHeadsMaster)
    }

    steps {
        dotnetBuild {
            projects = "AppLogger.csproj"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
        step {
            type = "jb.nuget.publish"
            param("secure:nuget.api.key", "credentialsJSON:abfe0cd9-7c87-4cc9-a20a-1ebb47bf7a2c")
            param("nuget.path", "%teamcity.tool.NuGet.CommandLine.DEFAULT%")
            param("nuget.publish.source", "http://localhost:8111/httpAuth/app/nuget/feed/DotNet/default/v2")
            param("nuget.publish.files", "bin/Debug/AppLogger.1.0.0.nupkg")
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        nuGetPackagesIndexer {
            feed = "DotNet/default"
        }
    }
})
