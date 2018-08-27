package DotNet.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object DotNet_HttpsGithubComAntonarhipovAppLoggerNugetSampleRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/antonarhipov/AppLoggerNugetSample#refs/heads/master"
    url = "https://github.com/antonarhipov/AppLoggerNugetSample"
    authMethod = password {
        userName = "antonarhipov"
        password = "credentialsJSON:9c527ed9-fdf3-4e93-96a8-646415563d05"
    }
    param("teamcity:vcsResourceDiscovery:versionedSettingsRoot", "false")
})
