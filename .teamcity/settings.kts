import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

version = "2018.1"

project {
    val app = buildType {
        id("App")
        name = "App"

        steps {
            
        }

        dependencies {
        }

        triggers {
        }
    }

    val buildApplication = buildType {
        id("BuildApplication")
        name = "BuildApplication"

        steps {
        }

        dependencies {
        }

        triggers {
        }
    }

    val testApplication = buildType {
        id("TestApplication")
        name = "TestApplication"

        type = BuildTypeSettings.Type.COMPOSITE

        steps {
        }


        triggers {
        }
    }
}

object BuildMyApp : BuildType({
    name = "BuildMyApp"

})
