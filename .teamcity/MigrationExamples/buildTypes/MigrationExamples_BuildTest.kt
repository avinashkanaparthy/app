package MigrationExamples.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle

object MigrationExamples_BuildTest : BuildType({
    name = "BuildTest"

    artifactRules = "build/libs/app*.jar"

    vcs {
        root(MigrationExamples.vcsRoots.MigrationExamples_HttpsGithubComAntonarhipovAppRefsHeadsMaster)
    }

    steps {
        gradle {
            tasks = "clean build"
            buildFile = ""
            gradleWrapperPath = ""
        }
    }
})
