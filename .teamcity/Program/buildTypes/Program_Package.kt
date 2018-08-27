package Program.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object Program_Package : BuildType({
    name = "Package"

    vcs {
        root(Program.vcsRoots.Program_HttpsGithubComAntonarhipovProgramPackageRefsHeadsMaster)
    }

    steps {
        dockerCommand {
            commandType = build {
                source = path {
                    path = "Dockerfile"
                }
                namesAndTags = "antonarhipov/prog:%build.number%"
                commandArgs = "--pull"
            }
        }
        dockerCommand {
            commandType = push {
                namesAndTags = "antonarhipov/prog:%build.number%"
            }
        }
    }

    triggers {
        vcs {
            watchChangesInDependencies = true
        }
    }

    features {
        dockerSupport {
            loginToRegistry = on {
                dockerRegistryId = "PROJECT_EXT_11"
            }
        }
    }

    dependencies {
        dependency(Program_Build) {
            snapshot {
            }

            artifacts {
                cleanDestination = true
                artifactRules = "program-0.0.1-SNAPSHOT.jar => program.jar"
            }
        }
    }
})
