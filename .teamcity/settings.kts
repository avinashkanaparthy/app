import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.finishBuildTrigger
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

version = "2018.1"

project {
    buildType {
        id("Build")
        name = "Build"



        artifactRules = "target/*.jar"

        steps {
            maven {
                goals = "clean package"
            }
        }

        cleanup {
            artifacts(days = 3, builds = 50)
            history(days = 5, builds = 100)
        }
    }
}


//endregion

//region pipeline
fun Project.pipeline(init: Pipeline.() -> Unit = {}) {
    val pipeline = Pipeline()
    pipeline.init()

    //register all builds in pipeline
    pipeline.stages.forEach { it ->
        it.buildTypes.forEach {
            buildType(it)
        }
    }
}

@TeamCityDsl
class Pipeline {
    val stages = arrayListOf<Stage>()

    fun stage(description: String, init: Stage.() -> Unit = {}) {
        val newStage = Stage()
        newStage.init()

        stages.lastOrNull()?.let { prevStage ->
            prevStage.buildTypes.lastOrNull()?.let { lastBuildType ->
                newStage.buildTypes.firstOrNull()?.let {
                    it.dependencies {
                        snapshot(lastBuildType) {}
                    }
                }
            }
        }
        stages.add(newStage)
    }
}
//endregion

//region phase
class Stage {
    val buildTypes = arrayListOf<BuildType>()

    operator fun BuildType.unaryPlus() {
        buildTypes.lastOrNull()?.let {
            this.dependencies {
//                snapshot(it) {}
            }
        }

        buildTypes.add(this)
    }
}
//endregion

