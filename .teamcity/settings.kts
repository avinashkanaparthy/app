import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.merge
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script

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

    buildType(Build)

    features {
        feature {
            id = "PROJECT_EXT_8"
            type = "active_storage"
            param("active.storage.feature.id", "DefaultStorage")
        }
    }
}

object Build : BuildType({
    name = "Build"

    artifactRules = "build/libs/*.jar"
    detectHangingBuilds = false

    params {
        text("projectVersion", "", label = "projectVersion", allowEmpty = false)
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            tasks = "clean build"
            buildFile = ""
            jdkHome = "%env.JDK_18%"
        }
        script {
            scriptContent = """echo "Project version is" %projectVersion%"""
        }
    }

    features {
        commitStatusPublisher {
            vcsRootExtId = "${DslContext.settingsRoot.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:e9bd74bc-134c-4ff2-bf29-8bd73120db5f"
                }
            }
            param("github_oauth_user", "antonarhipov")
        }
        merge {
            branchFilter = "+:feature1"
        }
    }

    cleanup {
        all(days = 2)
        history(days = 2)
        artifacts(days = 2)
    }
})
