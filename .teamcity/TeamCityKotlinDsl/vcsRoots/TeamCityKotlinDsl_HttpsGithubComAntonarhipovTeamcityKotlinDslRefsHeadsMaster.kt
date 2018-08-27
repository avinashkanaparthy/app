package TeamCityKotlinDsl.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object TeamCityKotlinDsl_HttpsGithubComAntonarhipovTeamcityKotlinDslRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/antonarhipov/teamcity-kotlin-dsl#refs/heads/master"
    url = "https://github.com/antonarhipov/teamcity-kotlin-dsl"
    authMethod = password {
        userName = "antonarhipov"
        password = "credentialsJSON:9c527ed9-fdf3-4e93-96a8-646415563d05"
    }
})
