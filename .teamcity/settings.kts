import jetbrains.buildServer.configs.kotlin.v2018_1.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_1.DslContext
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.project
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot
import jetbrains.buildServer.configs.kotlin.v2018_1.version

version = "2018.1"

project {
    vcsRoot(GitHubAppVcsRoot)
    buildType(Build)
    buildType(Upload)
}


object Build : BuildType({
//    id("Build")
    name = "Build"

    artifactRules = "build/libs/app*.jar"

    vcs {
//        root(DslContext.settingsRoot)
        root(GitHubAppVcsRoot)
    }

    steps {
        gradle {
            tasks = "clean build"
        }
    }

//    triggers { vcs {  } }

    cleanup {
        artifacts(days = 2)
        history(days = 2)
    }
})

object Upload : BuildType({

//    id("Upload")
    name = "Upload"

    steps {
        script {
            scriptContent = """
                echo 'hello!'
            """.trimIndent()
        }
    }
    dependencies {
        dependency(Build) { snapshot { } }
        dependency(Build) {
            artifacts {
                artifactRules = "app*.jar"
                sameChainOrLastFinished()
            }
        }
    }

    triggers {
        vcs {
            watchChangesInDependencies = true
        }
    }
})


object GitHubAppVcsRoot : GitVcsRoot({
    name = "GitHubAppVcsRoot"
    url = "https://github.com/antonarhipov/app"
    branchSpec = """
        +:refs/heads/*
        +:refs/pull/*/merge
    """.trimIndent()
    useTagsAsBranches = true
})



