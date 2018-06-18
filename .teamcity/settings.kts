import jetbrains.buildServer.configs.kotlin.v2018_1.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_1.DslContext
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.project
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_1.version

version = "2018.1"

project {

    buildType {
        name = "Build"

        artifactRules = "build/libs/app*.jar"

        vcs {
            root(DslContext.settingsRoot)
        }

        steps {
            gradle {
                tasks = "clean build"
            }
        }

        cleanup {
            history(days = 2)
            artifacts(days = 2)
        }
    }
}
