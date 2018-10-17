import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

version = "2018.1"

project {
    pipeline {
        phase("build stuff") {
            +Build
 //           +PrepareArtifacts
            +Publish
        }
    }
}

object Build: BuildType({
    id("Build")
    name = "Build"
})

object PrepareArtifacts: BuildType({
    id("PrepareArtifacts")
    name = "PrepareArtifacts"
})

object Publish: BuildType({
    id("Publish")
    name = "Publish"
})

class Pipeline {
    val phases = arrayListOf<Phase>()

    fun phase(description: String = "", init: Phase.() -> Unit = {}) {
        val newPhase = Phase()
        newPhase.init()

//        phases.lastOrNull()?.let { prevPhase ->
//            newPhase.buildTypes.forEach {
//                it.dependencies {
//                    for (dependency in prevPhase.buildTypes) {
//                        snapshot(it){}
//                    }
//                }
//            }
//        }
        phases.add(newPhase)
    }
}

class Phase {
    val buildTypes = hashSetOf<BuildType>()

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