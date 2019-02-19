import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_2.Project
import java.lang.IllegalStateException

class Chain {
    val stages = arrayListOf<Stage>()
}

interface Stage

class Serial(val buildType: BuildType) : Stage

class Parallel: Stage {
    val buildTypes = arrayListOf<BuildType>()
    val chains = arrayListOf<Chain>()
}

fun Parallel.build(bt: BuildType, block: BuildType.() -> Unit): BuildType {
    bt.apply(block)
    buildTypes.add(bt)
    return bt
}

fun Parallel.chain(block: Chain.()->Unit): Chain {
    val chain = Chain().apply(block)
    chains.add(chain)
    return chain
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
            throw IllegalStateException("paral lel cannot snapshot-depend on parallel")
        }
    }

    stages.add(parallel)
    return parallel
}

fun Chain.build(bt: BuildType, block: BuildType.() -> Unit): BuildType {
    bt.apply(block)

    registerDependency(bt, stages)

    stages.add(Serial(bt))
    return bt
}

private fun Chain.registerDependency(bt: BuildType, stages: List<Stage>) {
    stages.lastOrNull()?.let { stage ->
        if(stage is Serial) {
            bt.dependencies.snapshot(stage.buildType){}
        }
        if (stage is Parallel) {
            stage.buildTypes.forEach {
                bt.dependencies.snapshot(it){}
            }
            stage.chains.forEach {
                registerDependency(bt, it.stages)
            }
        }
    }
}

fun Project.chain(block: Chain.() -> Unit): Chain {
    val chain = Chain().apply(block)

    //register all builds in pipeline
    registerBuilds(chain)

    return chain
}

private fun Project.registerBuilds(chain: Chain) {
    chain.stages.forEach {
        if (it is Serial) {
            buildType(it.buildType)
        }
        if (it is Parallel) {
            it.buildTypes.forEach {
                buildType(it)
            }
            it.chains.forEach {
                registerBuilds(it)
            }
        }
    }
}

fun BuildType.produces(artifacts: String) {
    artifactRules = artifacts
}


fun BuildType.requires(bt: BuildType, artifacts: String) {
    dependencies.artifacts(bt) {
        artifactRules = artifacts
    }
}