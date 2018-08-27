package MigrationExamples.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script

object MigrationExamples_Controlflow : BuildType({
    name = "Controlflow - Build Steps"

    steps {
        script {
            name = "First"
            scriptContent = "echo 'First step'"
        }
        script {
            name = "Second"
            scriptContent = "cat file.txt"
        }
        script {
            name = "Third"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            scriptContent = "echo 'Third step'"
        }
    }
})
