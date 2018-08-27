package MavenProjects.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object MavenProjects_SpringPetclinic_1 : GitVcsRoot({
    id("MavenProjects_SpringPetclinic")
    name = "SpringPetclinic"
    url = "https://github.com/antonarhipov/spring-petclinic"
    authMethod = password {
        userName = "antonarhipov"
        password = "credentialsJSON:9c527ed9-fdf3-4e93-96a8-646415563d05"
    }
})
