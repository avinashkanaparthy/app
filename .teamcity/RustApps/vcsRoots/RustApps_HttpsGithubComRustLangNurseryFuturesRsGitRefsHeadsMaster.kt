package RustApps.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object RustApps_HttpsGithubComRustLangNurseryFuturesRsGitRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/rust-lang-nursery/futures-rs.git#refs/heads/master"
    url = "https://github.com/rust-lang-nursery/futures-rs.git"
})
