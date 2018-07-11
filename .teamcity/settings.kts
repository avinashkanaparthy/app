import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), build(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2018.1"

project {

    val build1 = build("BuildApp","BuildApp") {
        artifactRules = "build/libs/app-*.jar"

        vcs {
            root(DslContext.settingsRoot)
        }

        steps {
            gradle {
                tasks = "clean build"
            }
        }
    }
    val build2 = build("Package", "Package") {
        steps {
            script {
                scriptContent = """
                echo 'packaging %build.number%'
            """.trimIndent()
            }
        }

        dependencies {
            snapshot(build1) {}
            artifacts(build1) {
                artifactRules = "*.jar"
            }
        }
    }
    build("Install"," Install") {
        steps {
            script {
                scriptContent = """
                echo 'packaging %build.number%'
            """.trimIndent()
            }
        }

        dependencies {
            snapshot(build2) {}
        }

        triggers {
            vcs {
                watchChangesInDependencies = true
            }
        }
    }
}


fun build(id: String, name: String, init: BuildType.() -> Unit): BuildType {
    val bt = BuildType(init)
    bt.id(id)
    bt.name = name
    return bt
}
/*
object BuildApp : BuildType({
    name = "BuildApp"

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

object Package : BuildType({
    name = "Package"

    steps {
        script {
            scriptContent = """
                echo 'packaging %build.number%'
            """.trimIndent()
        }
    }

    dependencies {
        snapshot(BuildApp) {}
        artifacts(BuildApp) {
            artifactRules = "*.jar"
        }
    }

    triggers {
        vcs {
            watchChangesInDependencies = true
        }
    }
})

object Install : BuildType({
    name = "Install"

    steps {
        script {
            scriptContent = """
                echo 'packaging %build.number%'
            """.trimIndent()
        }
    }

    dependencies {
        snapshot(Package) {}
    }
})
 */