package Nanoservice

import Nanoservice.buildTypes.*
import Nanoservice.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project
import jetbrains.buildServer.configs.kotlin.v2018_1.projectFeatures.dockerRegistry

object Project : Project({
    id("Nanoservice")
    name = "Nanoservice"

    vcsRoot(Nanoservice_GitVCS)

    buildType(Nanoservice_Build)

    features {
        dockerRegistry {
            id = "PROJECT_EXT_4"
            name = "Docker Registry"
            url = "https://docker.io"
            userName = "antonarhipov"
            password = "credentialsJSON:84894465-ca51-4988-ada9-b6c756550b3a"
        }
    }
})
