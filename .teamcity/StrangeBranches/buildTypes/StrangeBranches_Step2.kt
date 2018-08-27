package StrangeBranches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object StrangeBranches_Step2 : BuildType({
    name = "Step2"

    vcs {
        root(StrangeBranches.vcsRoots.StrangeBranches_HttpsGithubComAntonarhipovRepositoryWithBranches2refsHeadsMaster)
    }

    steps {
        script {
            scriptContent = "echo %teamcity.build.branch%"
        }
    }

    triggers {
        vcs {
            watchChangesInDependencies = true
        }
    }

    dependencies {
        snapshot(StrangeBranches_Step1) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(StrangeBranches_Step3) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})
