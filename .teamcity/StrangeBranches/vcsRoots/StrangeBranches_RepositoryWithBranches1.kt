package StrangeBranches.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object StrangeBranches_RepositoryWithBranches1 : GitVcsRoot({
    name = "repository-with-branches-1"
    url = "https://github.com/antonarhipov/repository-with-branches-1"
    branchSpec = "+:*"
    useTagsAsBranches = true
    authMethod = password {
        userName = "antonarhipov"
        password = "credentialsJSON:9c527ed9-fdf3-4e93-96a8-646415563d05"
    }
})
