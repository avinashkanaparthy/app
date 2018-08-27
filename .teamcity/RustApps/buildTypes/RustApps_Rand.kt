package RustApps.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object RustApps_Rand : BuildType({
    name = "Rand"

    vcs {
        root(RustApps.vcsRoots.RustApps_HttpsGithubComRustLangNurseryRandGitRefsHeadsMaster)
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
