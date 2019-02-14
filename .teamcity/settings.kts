import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_2.DslContext
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_2.project
import jetbrains.buildServer.configs.kotlin.v2018_2.version
import org.arhan.pipeline

version = "2018.2"

project {

    pipeline {
        stage("Stage 1") {
            +Config1
            +Config2
        }
        stage("Stage 2") {
            +Config3
        }
    }


    buildType {
        id("Build")
        name = "Build"

        vcs {
            root(DslContext.settingsRoot)
        }

        artifactRules = "target/*.jar"

        steps {
            gradle {
                tasks = "clean build"
            }
        }

        cleanup {
            artifacts(days = 3, builds = 50)
            history(days = 5, builds = 100)
        }
    }
}

object Config1 : BuildType({
    name = "Config1"

})

object Config2 : BuildType({
    name = "Config2"

})

object Config3 : BuildType({
    name = "Config3"

})

//endregion
