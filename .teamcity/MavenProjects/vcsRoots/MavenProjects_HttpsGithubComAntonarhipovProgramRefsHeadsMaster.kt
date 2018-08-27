package MavenProjects.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object MavenProjects_HttpsGithubComAntonarhipovProgramRefsHeadsMaster : GitVcsRoot({
    name = "ProgramVCS"
    url = "https://github.com/antonarhipov/program"
    authMethod = password {
        userName = "antonarhipov"
        password = "credentialsJSON:0e5aa3d2-3ac6-4ea4-90a6-05d9018fb44f"
    }
})
