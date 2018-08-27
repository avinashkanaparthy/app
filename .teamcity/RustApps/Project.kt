package RustApps

import RustApps.buildTypes.*
import RustApps.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project({
    id("RustApps")
    name = "RustApps"

    vcsRoot(RustApps_HttpsGithubComRustLangNurseryFuturesRsGitRefsHeadsMaster)
    vcsRoot(RustApps_HttpsGithubComSharkdpFdGitRefsHeadsMaster)
    vcsRoot(RustApps_HttpsGithubComRustLangNurseryRandGitRefsHeadsMaster)

    buildType(RustApps_Rand)
    buildType(RustApps_Fd)
    buildType(RustApps_FuturesFS)
})
