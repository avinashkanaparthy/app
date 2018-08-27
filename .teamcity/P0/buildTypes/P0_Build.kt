package P0.buildTypes

import P0.vcsRoots.P0_ProgramVCS
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.vcsLabeling
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.maven

object P0_Build : BuildType({
    name = "Build"

    artifactRules = "target/program-*.jar"

    vcs {
        root(P0.vcsRoots.P0_ProgramVCS)
    }

    steps {
        maven {
            goals = "clean package"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
        }
    }

    features {
        vcsLabeling {
            vcsRootId = "${P0_ProgramVCS.id}"
            labelingPattern = "build-%teamcity.build.branch%-%system.build.number%"
            successfulOnly = true
            branchFilter = ""
        }
    }
})
