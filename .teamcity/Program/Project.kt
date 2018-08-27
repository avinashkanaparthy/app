package Program

import Program.buildTypes.*
import Program.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project
import jetbrains.buildServer.configs.kotlin.v2018_1.projectFeatures.dockerRegistry

object Project : Project({
    id("Program")
    name = "Program"

    vcsRoot(Program_HttpsGithubComAntonarhipovProgramPackageRefsHeadsMaster)
    vcsRoot(Program_HttpsGithubComAntonarhipovProgramRefsHeadsMaster)

    buildType(Program_Package)
    buildType(Program_Build)

    features {
        dockerRegistry {
            id = "PROJECT_EXT_11"
            name = "Docker Registry"
            url = "https://docker.io"
            userName = "antonarhipov"
            password = "credentialsJSON:84894465-ca51-4988-ada9-b6c756550b3a"
        }
    }
})
