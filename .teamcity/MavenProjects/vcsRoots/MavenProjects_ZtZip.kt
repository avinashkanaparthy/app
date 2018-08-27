package MavenProjects.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object MavenProjects_ZtZip : GitVcsRoot({
    name = "ZtZip"
    url = "https://github.com/antonarhipov/zt-zip"
    authMethod = password {
        userName = "antonarhipov"
    }
})
