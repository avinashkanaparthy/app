package MavenProjects

import MavenProjects.buildTypes.*
import MavenProjects.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project({
    id("MavenProjects")
    name = "MavenProjects"
    defaultTemplate = RelativeId("MavenProjects_MainTemplate")

    vcsRoot(MavenProjects_HttpsGithubComAntonarhipovProgramRefsHeadsMaster)
    vcsRoot(MavenProjects_SpringPetclinic_1)
    vcsRoot(MavenProjects_ZtZip)

    buildType(MavenProjects_SpringPetclinic)
    buildType(MavenProjects_Program)
    buildType(MavenProjects_ZtZtip)

    template(MavenProjects_MainTemplate)
})
