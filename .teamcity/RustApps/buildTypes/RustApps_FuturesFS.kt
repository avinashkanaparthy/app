package RustApps.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*

object RustApps_FuturesFS : BuildType({
    name = "FuturesFS"

    vcs {
        root(RustApps.vcsRoots.RustApps_HttpsGithubComRustLangNurseryFuturesRsGitRefsHeadsMaster)
    }

    steps {
        step {
            type = "cargo"
            param("cargo-build-features", "futures-core futures-io")
            param("cargo-build-parallel", "2")
            param("cargo-toolchain", "1.23")
            param("cargo-build-no-default-features", "true")
            param("cargo-command", "build")
        }
        step {
            type = "cargo"
            param("cargo-command", "test")
        }
    }
})
