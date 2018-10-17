import jetbrains.buildServer.configs.kotlin.v2018_1.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_1.Project
import jetbrains.buildServer.configs.kotlin.v2018_1.project
import jetbrains.buildServer.configs.kotlin.v2018_1.version

version = "2018.1"

project {
    pipeline {
        phase("build stuff") {
            +Build
            +PrepareArtifacts
            +Test
        }
//        phase("deploy stuff") {
//            +Publish
//        }
    }
}

object Build : BuildType({
    id("Build")
    name = "Build"
})

object PrepareArtifacts : BuildType({
    id("PrepareArtifacts")
    name = "PrepareArtifacts"
})

object Test : BuildType({
    id("Test")
    name = "Test"
})

object Publish : BuildType({
    id("Publish")
    name = "Publish"
})

class Pipeline {
    val phases = arrayListOf<Phase>()

    fun phase(description: String = "", init: Phase.() -> Unit = {}) {
        val newPhase = Phase()
        newPhase.init()

//        phases.lastOrNull()?.let { prevPhase ->
//            prevPhase.buildTypes.lastOrNull()?.let { lastBuildType ->
//                newPhase.buildTypes.firstOrNull()?.let {
//                    it.dependencies {
//                        snapshot(lastBuildType) {}
//                    }
//                }
//            }
//        }
        phases.add(newPhase)
    }
}

class Phase {
    val buildTypes = linkedSetOf<BuildType>()

    operator fun BuildType.unaryPlus() {
        buildTypes.lastOrNull()?.let {
            this.dependencies {
                snapshot(it) {}
            }
        }

        buildTypes.add(this)
    }
}

fun Project.pipeline(init: Pipeline.() -> Unit = {}) {
    val pipeline = Pipeline()
    pipeline.init()
    //register all builds in pipeline
    pipeline.phases.forEach { phase ->
        phase.buildTypes.forEach { bt ->
            this.buildType(bt)
        }
    }
}