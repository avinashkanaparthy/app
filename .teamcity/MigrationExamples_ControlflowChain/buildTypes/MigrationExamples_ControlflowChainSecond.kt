package MigrationExamples_ControlflowChain.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.finishBuildTrigger

object MigrationExamples_ControlflowChainSecond : BuildType({
    name = "Controlflow Chain - Second"

    steps {
        script {
            scriptContent = """
                echo 'Second step'
                cat file.txt
            """.trimIndent()
        }
    }

    triggers {
        finishBuildTrigger {
            buildTypeExtId = "${MigrationExamples_ControlflowChainFirst.id}"
            successfulOnly = true
        }
    }

    dependencies {
        snapshot(MigrationExamples_ControlflowChainFirst) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.IGNORE
            onDependencyCancel = FailureAction.ADD_PROBLEM
        }
    }
})
