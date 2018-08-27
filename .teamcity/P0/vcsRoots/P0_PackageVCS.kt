package P0.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object P0_PackageVCS : GitVcsRoot({
    name = "PackageVCS"
    url = "https://github.com/antonarhipov/program-package"
    branchSpec = """
        +:ref/heads/(master)
        +:ref/heads/(feature)
    """.trimIndent()
    authMethod = password {
        userName = "antonarhipov"
        password = "credentialsJSON:0e5aa3d2-3ac6-4ea4-90a6-05d9018fb44f"
    }
})
