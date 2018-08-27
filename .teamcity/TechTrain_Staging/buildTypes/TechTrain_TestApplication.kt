package TechTrain_Staging.buildTypes

import TechTrain.vcsRoots.TechTrain_Docker_1
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object TechTrain_TestApplication : BuildType({
    name = "TestApplication"

    triggers {
        vcs {
            triggerRules = "+:root=${TechTrain_Docker_1.id}:Dockerfile"

            branchFilter = ""
            watchChangesInDependencies = true
        }
    }

    dependencies {
        snapshot(TechTrain_Docker) {
        }
    }
})
