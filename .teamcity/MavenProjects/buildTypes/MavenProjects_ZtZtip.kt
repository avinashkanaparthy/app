package MavenProjects.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2018_1.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.v2018_1.failureConditions.failOnText
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object MavenProjects_ZtZtip : BuildType({
    name = "ZtZtip"

    vcs {
        root(MavenProjects.vcsRoots.MavenProjects_ZtZip)
    }

    steps {
        maven {
            name = "Integration Tests"
            goals = "verify"
            runnerArgs = "-P integration-test"
            mavenVersion = defaultProvidedVersion()
        }
        maven {
            name = "Maven Deploy"
            goals = "deploy"
            mavenVersion = defaultProvidedVersion()
            jvmArgs = "-DaltDeploymentRepository=local"
        }
    }

    triggers {
        vcs {
            branchFilter = ""
        }
    }

    failureConditions {
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "error in build"
            failureMessage = "error in build"
            reverse = false
            stopBuildOnFailure = true
        }
    }
})
