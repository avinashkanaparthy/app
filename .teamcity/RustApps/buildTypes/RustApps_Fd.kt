package RustApps.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object RustApps_Fd : BuildType({
    name = "Fd"

    vcs {
        root(RustApps.vcsRoots.RustApps_HttpsGithubComSharkdpFdGitRefsHeadsMaster)
    }

    steps {
        step {
            type = "cargo"
            param("cargo-command", "build")
        }
        step {
            type = "cargo"
            param("cargo-command", "test")
        }
    }

    triggers {
        vcs {
        }
    }
})
