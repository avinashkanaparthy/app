import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_2.Project
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import java.lang.IllegalStateException

class Chain {
    val stages = arrayListOf<Stage>()
}

interface Stage

class Serial(val buildType: BuildType) : Stage

class Parallel: Stage {
    val buildTypes = arrayListOf<BuildType>()
}

fun Parallel.build(bt: BuildType, block: BuildType.() -> Unit): BuildType {
    bt.apply(block)
    buildTypes.add(bt)
    return bt
}

fun Chain.parallel(block: Parallel.() -> Unit): Stage {
    val parallel = Parallel().apply(block)

    stages.lastOrNull()?.let { stage ->
        if(stage is Serial){
            parallel.buildTypes.forEach {
                it.dependencies.snapshot(stage.buildType){}
            }
        }
        if (stage is Parallel) {
            throw IllegalStateException("parallel cannot snapshot-depend on parallel")
        }
    }

    stages.add(parallel)
    return parallel
}

fun Chain.build(bt: BuildType, block: BuildType.() -> Unit): BuildType {
    bt.apply(block)

    stages.lastOrNull()?.let { stage ->
        if(stage is Serial) {
            bt.dependencies.snapshot(stage.buildType){}
        }
        if (stage is Parallel) {
            stage.buildTypes.forEach {
                bt.dependencies.snapshot(it){}
            }
        }
    }

    stages.add(Serial(bt))
    return bt
}

fun Project.chain(block: Chain.() -> Unit): Chain {
//    val chain = Chian()
//    chain.block()
    val chain = Chain().apply(block)

    //register all builds in pipeline
    chain.stages.forEach {
        if(it is Serial) {
            buildType(it.buildType)
        }
        if (it is Parallel) {
            it.buildTypes.forEach {
                buildType(it)
            }
        }
    }

    chain.stages.lastOrNull()?.let {
        if (it is Serial) {
            it.buildType.triggers{
                vcs {
                    watchChangesInDependencies = true
                }
            }
        }
    }

    return chain
}

fun BuildType.produce(artifacts: String) {
    artifactRules = artifacts
}


fun BuildType.requires(bt: BuildType, artifacts: String) {
    dependencies.artifacts(bt) {
        artifactRules = artifacts
    }
}