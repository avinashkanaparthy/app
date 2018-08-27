package StrangeBranches

import StrangeBranches.buildTypes.*
import StrangeBranches.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project({
    id("StrangeBranches")
    name = "StrangeBranches"

    vcsRoot(StrangeBranches_HttpsGithubComAntonarhipovRepositoryWithBranches2refsHeadsMaster)
    vcsRoot(StrangeBranches_RepositoryWithBranches3)
    vcsRoot(StrangeBranches_RepositoryWithBranches1)

    buildType(StrangeBranches_Step1)
    buildType(StrangeBranches_Step2)
    buildType(StrangeBranches_Step3)

    features {
        feature {
            id = "PROJECT_EXT_2"
            type = "project-graphs"
            param("series", """
                [
                  {
                    "type": "valueType",
                    "title": "Build Duration (all stages)",
                    "sourceBuildTypeId": "StrangeBranches_Step2",
                    "key": "BuildDuration"
                  },
                  {
                    "type": "valueType",
                    "title": "Build Checkout Time",
                    "sourceBuildTypeId": "StrangeBranches_Step2",
                    "key": "buildStageDuration:sourcesUpdate"
                  },
                  {
                    "type": "valueType",
                    "title": "Time Spent in Queue",
                    "sourceBuildTypeId": "StrangeBranches_Step2",
                    "key": "TimeSpentInQueue"
                  }
                ]
            """.trimIndent())
            param("format", "text")
            param("hideFilters", "")
            param("title", "New chart title")
            param("defaultFilters", "")
            param("seriesTitle", "Serie")
        }
    }
})
