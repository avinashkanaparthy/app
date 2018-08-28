import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

version = "2018.1"

project {

    val build1 = buildType {
        id("Build")
        name = "Build My App"

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

    buildType {
        id("Test")
        name = "Test My App"

        dependencies {
            dependency(build1){
                snapshot {

                }
                artifacts {
                    artifactRules = "app-*.jar"
                }
            }
        }

        steps {
            script {
                scriptContent = "du -k *.jar"
            }
        }

        triggers {
            vcs {
                watchChangesInDependencies = true
            }
        }
    }
}
