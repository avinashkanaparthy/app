package MavenProjects.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.placeholder
import jetbrains.buildServer.configs.kotlin.v2018_1.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.v2018_1.failureConditions.failOnText
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object MavenProjects_MainTemplate : Template({
    name = "MainTemplate"

    artifactRules = """
        target/*.war
        target/*.jar
    """.trimIndent()

    steps {
        maven {
            name = "Maven Package"
            id = "RUNNER_17"
            goals = "clean package"
            mavenVersion = defaultProvidedVersion()
        }
        placeholder {
            id = "RUNNER_23"
        }
        maven {
            name = "Maven Deploy"
            id = "RUNNER_22"
            goals = "deploy"
            mavenVersion = defaultProvidedVersion()
        }
    }

    triggers {
        vcs {
            id = "vcsTrigger"
            branchFilter = ""
        }
    }

    failureConditions {
        failOnText {
            id = "BUILD_EXT_12"
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "error in build"
            failureMessage = "error in build"
            reverse = false
            stopBuildOnFailure = true
        }
    }
})
