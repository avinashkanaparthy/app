import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

version = "2018.1"

project {

    buildType {
        id("Build")
        name = "Build My App"

        artifactRules = "build/libs/app-*.jar"

        vcs {
            root(DslContext.settingsRoot)
        }

        steps {
            gradle {
                tasks = "clean build"
//                buildFile = ""
//                gradleWrapperPath = ""
            }
        }

        triggers {
            vcs {
            }
        }
    }
}
