package MavenProjects.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.v2018_1.failureConditions.failOnText
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object MavenProjects_SpringPetclinic : BuildType({
    name = "SpringPetclinic"

    vcs {
        root(MavenProjects.vcsRoots.MavenProjects_SpringPetclinic_1)
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
