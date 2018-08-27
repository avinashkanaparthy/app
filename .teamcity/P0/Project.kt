package P0

import P0.buildTypes.*
import P0.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project
import jetbrains.buildServer.configs.kotlin.v2018_1.projectFeatures.dockerRegistry

object Project : Project({
    id("P0")
    name = "P0"

    vcsRoot(P0_ProgramVCS)
    vcsRoot(P0_PackageVCS)

    buildType(P0_Build)
    buildType(P0_Package)

    features {
        dockerRegistry {
            id = "PROJECT_EXT_9"
            name = "Docker Registry"
            url = "https://docker.io"
            userName = "antonarhipov"
            password = "credentialsJSON:84894465-ca51-4988-ada9-b6c756550b3a"
        }
    }
})
