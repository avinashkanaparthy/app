package MigrationExamples_BuildChain.buildTypes

import MigrationExamples_BuildChain.vcsRoots.MigrationExamples_BuildChain_BasicBuildChain
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.replaceContent
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script

object MigrationExamples_BuildChain_First : BuildType({
    name = "First"

    artifactRules = "result.txt"

    vcs {
        root(MigrationExamples_BuildChain.vcsRoots.MigrationExamples_BuildChain_BasicBuildChain)
    }

    steps {
        script {
            scriptContent = """
                echo "First"
                cp file.txt result.txt
            """.trimIndent()
        }
    }

    features {
        replaceContent {
            fileRules = "+:file.txt"
            pattern = "version"
            replacement = "${MigrationExamples_BuildChain_BasicBuildChain.paramRefs.buildVcsNumber}"
        }
    }
})
