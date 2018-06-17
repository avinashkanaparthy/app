import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

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

version = "2017.2"

project {

    vcsRoot(GitHub)
    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    artifactRules = "build/libs/*.jar"


    vcs {
        root(GitHub)
    }

    steps {
        gradle {
            tasks = "clean build"
            buildFile = ""
            useGradleWrapper = false
        }
    }

    triggers {
        vcs {
        }
    }

    cleanup {
        all(days = 2)
        history(days = 2)
        artifacts(days = 2)
    }
})

object GitHub : GitVcsRoot({
    name = "AppGitHub"
    url = "https://github.com/antonarhipov/app"
    authMethod = password {
        userName = "antonarhipov"
        password = "zxxa13ec3d722b5aef74a02c867ad682a07b568905dceb0ee997cd4664dd8ea5fe0179060b66debea84775d03cbe80d301b"
    }
    param("teamcity:vcsResourceDiscovery:versionedSettingsRoot", "true")
})