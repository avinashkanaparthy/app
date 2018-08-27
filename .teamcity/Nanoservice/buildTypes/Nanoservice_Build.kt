package Nanoservice.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object Nanoservice_Build : BuildType({
    name = "Build"

    vcs {
        root(Nanoservice.vcsRoots.Nanoservice_GitVCS)
    }

    steps {
        dockerCommand {
            commandType = build {
                source = path {
                    path = "Dockerfile"
                }
                namesAndTags = "antonarhipov/nanoservice:%build.number%"
                commandArgs = "--pull"
            }
        }
        dockerCommand {
            commandType = push {
                namesAndTags = "antonarhipov/nanoservice:%build.number%"
            }
        }
    }

    triggers {
        vcs {
            enabled = false
        }
    }

    features {
        dockerSupport {
            cleanupPushedImages = true
            loginToRegistry = on {
                dockerRegistryId = "PROJECT_EXT_4"
            }
        }
    }
})
