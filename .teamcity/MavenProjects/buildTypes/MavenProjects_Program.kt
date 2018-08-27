package MavenProjects.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*

object MavenProjects_Program : BuildType({
    name = "Program"

    vcs {
        root(MavenProjects.vcsRoots.MavenProjects_HttpsGithubComAntonarhipovProgramRefsHeadsMaster)
    }
})
