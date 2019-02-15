import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot
import org.arhan.pipeline

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2018.2"

project {
    vcsRoot(ApplicationVcs)
    buildType(BuildApplication)
    buildType(BuildDockerImage)
}

object BuildDockerImage : BuildType({
    name = "BuildDockerImage"

    vcs { root(ApplicationVcs) }

    steps {
        dockerCommand {
            commandType = build {
                source = path {
                    path = "Dockerfile"
                }
                namesAndTags = "antonarhipov/application:%build.number%"
                commandArgs = "--pull"
            }
        }
        dockerCommand {
            commandType = push {
                namesAndTags = "antonarhipov/application:%build.number%"
            }
        }
    }

    dependencies {
        dependency(BuildApplication) {
            snapshot { runOnSameAgent = true }
            artifacts { artifactRules = "application.jar" }
        }
    }


})

object BuildApplication : BuildType({
    name = "BuildApplication"

    artifactRules = "target/*.jar"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            scriptContent = "echo [a, b, c]"
        }
        gradle {
            tasks = "clean build"
        }
    }
})


object ApplicationVcs : GitVcsRoot({})