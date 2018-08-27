package MigrationExamples_BuildChain.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script

object MigrationExamples_BuildChain_Second : BuildType({
    name = "Second"

    enablePersonalBuilds = false
    type = BuildTypeSettings.Type.DEPLOYMENT
    maxRunningBuilds = 1

    params {
        checkbox("Confirm", "", label = "Are you sure?", display = ParameterDisplay.PROMPT,
                  checked = "true")
    }

    steps {
        script {
            scriptContent = """
                echo "Second"
                echo ${MigrationExamples_BuildChain_First.depParamRefs["build.vcs.number"]}
                echo "##teamcity[buildStatus status='<status value>' text='{build.status.text} and ${MigrationExamples_BuildChain_First.depParamRefs["build.vcs.number"]}']"
            """.trimIndent()
        }
    }

    dependencies {
        artifacts(MigrationExamples_BuildChain_First) {
            buildRule = lastSuccessful()
            artifactRules = "result.txt"
        }
    }
})
