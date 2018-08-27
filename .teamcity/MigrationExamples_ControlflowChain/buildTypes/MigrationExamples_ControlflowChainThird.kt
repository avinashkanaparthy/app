package MigrationExamples_ControlflowChain.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.finishBuildTrigger

object MigrationExamples_ControlflowChainThird : BuildType({
    name = "Controlflow Chain - Third"

    steps {
        script {
            scriptContent = "echo 'Third step'"
        }
    }

    triggers {
        finishBuildTrigger {
            buildTypeExtId = "${MigrationExamples_ControlflowChainSecond.id}"
        }
    }

    dependencies {
        snapshot(MigrationExamples_ControlflowChainSecond) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.IGNORE
            onDependencyCancel = FailureAction.IGNORE
        }
    }
})
