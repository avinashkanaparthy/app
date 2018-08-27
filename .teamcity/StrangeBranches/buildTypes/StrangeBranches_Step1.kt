package StrangeBranches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.freeDiskSpace
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.merge
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script

object StrangeBranches_Step1 : BuildType({
    name = "Step1"

    vcs {
        root(StrangeBranches.vcsRoots.StrangeBranches_RepositoryWithBranches1)
    }

    steps {
        script {
            scriptContent = "echo %teamcity.build.branch%"
        }
    }

    features {
        merge {
            enabled = false
            branchFilter = "+:refs/heads/hotfix"
        }
        freeDiskSpace {
            requiredSpace = "100mb"
            failBuild = true
        }
    }
})
