package P0.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object P0_Package : BuildType({
    name = "Package"

    vcs {
        root(P0.vcsRoots.P0_PackageVCS)
    }

    steps {
        dockerCommand {
            enabled = false
            commandType = build {
                source = path {
                    path = "Dockerfile"
                }
                namesAndTags = "antonarhipov/program:%build.number%"
                commandArgs = "--pull"
            }
        }
        dockerCommand {
            enabled = false
            commandType = push {
                namesAndTags = "antonarhipov/program:%build.number%"
            }
        }
    }

    triggers {
        vcs {
            enabled = false
            branchFilter = ""
            watchChangesInDependencies = true
        }
    }

    features {
        dockerSupport {
            loginToRegistry = on {
                dockerRegistryId = "PROJECT_EXT_9"
            }
        }
    }

    dependencies {
        dependency(P0_Build) {
            snapshot {
            }

            artifacts {
                cleanDestination = true
                artifactRules = "program-0.0.1-SNAPSHOT.jar=>program.jar"
            }
        }
    }
})
