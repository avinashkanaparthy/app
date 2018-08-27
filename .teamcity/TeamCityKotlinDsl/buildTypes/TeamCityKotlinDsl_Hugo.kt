package TeamCityKotlinDsl.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object TeamCityKotlinDsl_Hugo : BuildType({
    name = "Hugo"

    artifactRules = "public => content.zip"

    vcs {
        root(TeamCityKotlinDsl.vcsRoots.TeamCityKotlinDsl_HttpsGithubComAntonarhipovTeamcityKotlinDslRefsHeadsMaster)
    }

    steps {
        script {
            name = "Build Hugo Web Site"
            scriptContent = "hugo"
        }
    }

    triggers {
        vcs {
        }
    }
})
