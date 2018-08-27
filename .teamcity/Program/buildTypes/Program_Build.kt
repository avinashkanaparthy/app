package Program.buildTypes

import Program.vcsRoots.Program_HttpsGithubComAntonarhipovProgramRefsHeadsMaster
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.vcsLabeling
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.maven

object Program_Build : BuildType({
    name = "Build"

    artifactRules = "target/program-0.0.1-SNAPSHOT.jar"

    vcs {
        root(Program.vcsRoots.Program_HttpsGithubComAntonarhipovProgramRefsHeadsMaster)
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
            vcsRootId = "${Program_HttpsGithubComAntonarhipovProgramRefsHeadsMaster.id}"
            labelingPattern = "program-%system.build.number%"
            branchFilter = ""
        }
    }
})
