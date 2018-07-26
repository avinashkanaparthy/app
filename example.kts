import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

version = "2018.1"

project {

    val build1 = build("BuildApp", "BuildApp") {
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
    build("Install", "Install") {
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


fun Project.build(id: String, name: String, init: BuildType.() -> Unit): BuildType {
    return buildType {
        id(id)
        this.name = name
        init()
    }
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