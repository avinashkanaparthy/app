package Nanoservice.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object Nanoservice_GitVCS : GitVcsRoot({
    name = "GitVCS"
    url = "https://github.com/antonarhipov/nanoservice"
    branchSpec = "+:ref/heads/master"
    userNameStyle = GitVcsRoot.UserNameStyle.NAME
    serverSideAutoCRLF = true
    agentCleanPolicy = GitVcsRoot.AgentCleanPolicy.ALWAYS
    authMethod = password {
        userName = "antonarhipov"
        password = "credentialsJSON:0e5aa3d2-3ac6-4ea4-90a6-05d9018fb44f"
    }
})
