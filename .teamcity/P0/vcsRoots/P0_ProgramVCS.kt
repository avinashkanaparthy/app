package P0.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object P0_ProgramVCS : GitVcsRoot({
    name = "ProgramVCS"
    url = "https://github.com/antonarhipov/program"
    branchSpec = """
        +:refs/heads/(master)
        +:refs/heads/(feature)
    """.trimIndent()
    authMethod = password {
        userName = "antonarhipov"
        password = "credentialsJSON:22dd1f8f-17b2-46af-bc46-38ef2f6955d3"
    }
})
