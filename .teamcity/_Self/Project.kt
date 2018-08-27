package _Self

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project
import jetbrains.buildServer.configs.kotlin.v2018_1.projectFeatures.nuGetFeed

object Project : Project({
    description = "Contains all other projects"

    features {
        feature {
            id = "PROJECT_EXT_1"
            type = "ReportTab"
            param("startPage", "coverage.zip!index.html")
            param("title", "Code Coverage")
            param("type", "BuildReportTab")
        }
        feature {
            id = "PROJECT_EXT_8"
            type = "OAuthProvider"
            param("clientId", "32bb0de6f94c5269acaf")
            param("defaultTokenScope", "public_repo,repo,repo:status,write:repo_hook")
            param("secure:clientSecret", "credentialsJSON:40a3fc2e-ee0d-4466-b449-4c0a0e0064e1")
            param("displayName", "GitHub.com")
            param("gitHubUrl", "https://github.com/")
            param("providerType", "GitHub")
        }
        nuGetFeed {
            id = "repository-nuget-default"
            name = "default"
        }
    }

    cleanup {
        preventDependencyCleanup = false
    }

    subProject(P0.Project)
    subProject(Nanoservice.Project)
    subProject(MavenProjects.Project)
    subProject(Program.Project)
    subProject(TechTrain.Project)
    subProject(TeamCityKotlinDsl.Project)
    subProject(MigrationExamples.Project)
    subProject(RustApps.Project)
    subProject(DotNet.Project)
    subProject(StrangeBranches.Project)
})
