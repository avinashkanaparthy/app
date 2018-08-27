package MigrationExamples_ControlflowChain.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script

object MigrationExamples_ControlflowChainFirst : BuildType({
    name = "Controlflow Chain - First"

    steps {
        script {
            scriptContent = "echo 'First step'"
        }
    }
})
