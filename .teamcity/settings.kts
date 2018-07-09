import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

version = "2018.1"

project {

    buildType(Build)
    buildType(Deploy)
}

object Build : BuildType({
    name = "Build"

    artifactRules = "build/libs/app-*.jar"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            tasks = "clean build"
            buildFile = ""
            gradleWrapperPath = ""
        }
    }

})

object Deploy : BuildType({
    name = "Deploy"
    dependencies {
        snapshot(Build){}
        artifacts(Build){
            artifactRules = "*.jar"
        }
    }

    if (DslContext.projectName == "App2") {
        artifactRules = "*.jar"
    }

    triggers {
        vcs {
            watchChangesInDependencies = true
        }
    }
})
