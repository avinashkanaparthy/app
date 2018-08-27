package MigrationExamples.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.replaceContent
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script

object MigrationExamples_Replacer : BuildType({
    name = "Replacer"

    artifactRules = "result.txt"

    vcs {
        root(MigrationExamples.vcsRoots.MigrationExamples_HttpsGithubComAntonarhipovReplacerRefsHeadsMaster)

        cleanCheckout = true
    }

    steps {
        script {
            scriptContent = "cat file.txt > result.txt"
        }
    }

    features {
        replaceContent {
            fileRules = "+:file.txt"
            pattern = "pattern1"
            replacement = "result1"
        }
        replaceContent {
            fileRules = "+:file.txt"
            pattern = "pattern2"
            replacement = "result2"
        }
    }
})
