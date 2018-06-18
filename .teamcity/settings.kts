import jetbrains.buildServer.configs.kotlin.v2018_1.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_1.DslContext
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.project
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_1.version

version = "2018.1"

project {
    buildType(Build)
    buildType(Upload)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            tasks = "clean build"
        }
    }

    artifactRules = "build/libs/app*.jar"

    cleanup {
        artifacts(days = 2)
        history(days = 2)
    }
})

object Upload : BuildType({
    name = "Upload"

    steps {
        script {
            scriptContent = """
                #some logic here
            """.trimIndent()
        }
    }

    dependencies {
        dependency(Build) { snapshot {  }}
        dependency(Build) { artifacts {
            artifactRules = "*.jar"
            sameChainOrLastFinished()
        }}
    }

    triggers {
        vcs {
            watchChangesInDependencies = true
        }
    }
})