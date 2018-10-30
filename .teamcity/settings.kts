import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.finishBuildTrigger

version = "2018.1"

project {
    subProject {
        id("BuildAndTest")
        name = "BuildAndTest"

        buildType(Build)
        buildType(PrepareArtifacts)
        buildType(Test)
    }

    subProject {
        id("Delivery")
        name = "Delivery"

        buildType(Publish)
    }
}

object  Build : BuildType({
    id("Build")
    name = "Build"
})

object PrepareArtifacts : BuildType({
    id("PrepareArtifacts")
    name = "PrepareArtifacts"

    dependencies {
        snapshot(Build) {}
    }
})

object Test : BuildType({
    id("Test")
    name = "Test"
})

object Publish : BuildType({
    id("Publish")
    name = "Publish"
})


//region pipeline
fun Project.pipeline(init: Pipeline.() -> Unit = {}) {
    val pipeline = Pipeline()
    pipeline.init()

    //register all builds in pipeline
    pipeline.phases.forEach { it ->
        it.buildTypes.forEach {
            buildType(it)
        }
    }
}

@TeamCityDsl
class Pipeline {
    val phases = arrayListOf<Phase>()

    fun stage(description: String, init: Phase.() -> Unit = {}) {
        val newPhase = Phase()
        newPhase.init()

        phases.lastOrNull()?.let { prevPhase ->
            prevPhase.buildTypes.lastOrNull()?.let { lastBuildType ->
                newPhase.buildTypes.firstOrNull()?.let {
                    it.dependencies {
                        snapshot(lastBuildType) {}
                    }
                    it.triggers.finishBuildTrigger {  }
                }
            }
        }
        phases.add(newPhase)
    }
}
//endregion

//region phase
class Phase {
    val buildTypes = arrayListOf<BuildType>()

    operator fun BuildType.unaryPlus() {
        buildTypes.lastOrNull()?.let {
            this.dependencies {
                snapshot(it) {}
            }
        }

        buildTypes.add(this)
    }
}
//endregion

