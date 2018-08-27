package RustApps.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object RustApps_HttpsGithubComRustLangNurseryRandGitRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/rust-lang-nursery/rand.git#refs/heads/master"
    url = "https://github.com/rust-lang-nursery/rand.git"
})
