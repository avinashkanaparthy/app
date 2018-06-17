import jetbrains.buildServer.configs.kotlin.v2018_1.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.project
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_1.version

version = "2017.2"

project {
    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    artifactRules = "build/libs/*.jar"

    params {
        text("projectVersion", "", label = "projectVersion", allowEmpty = false)
    }

    steps {
        gradle {
            tasks = "clean build"
            buildFile = ""
            gradleWrapperPath = ""
        }
    }

    triggers {
        vcs {  }
    }

    cleanup {
        all(days = 2)
        history(days = 2)
        artifacts(days = 2)
    }
})
