package RustApps.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object RustApps_HttpsGithubComSharkdpFdGitRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/sharkdp/fd.git#refs/heads/master"
    url = "https://github.com/sharkdp/fd.git"
})
