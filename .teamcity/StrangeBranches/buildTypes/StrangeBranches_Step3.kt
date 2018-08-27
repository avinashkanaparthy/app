package StrangeBranches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script

object StrangeBranches_Step3 : BuildType({
    name = "Step3"

    vcs {
        root(StrangeBranches.vcsRoots.StrangeBranches_RepositoryWithBranches3)
    }

    steps {
        script {
            scriptContent = "echo %teamcity.build.branch%"
        }
    }
})
