//import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
//import jetbrains.buildServer.configs.kotlin.v2018_2.Project
//import java.lang.IllegalStateException
//
//class Sequential {
//    val stages = arrayListOf<Stage>()
//}
//
//interface Stage
//
//class Single(val buildType: BuildType) : Stage
//
//class Parallel: Stage {
//    val buildTypes = arrayListOf<BuildType>()
//    val sequences = arrayListOf<Sequential>()
//}
//
//fun Parallel.build(bt: BuildType, block: BuildType.() -> Unit): BuildType {
//    bt.apply(block)
//    buildTypes.add(bt)
//    return bt
//}
//
//fun Parallel.sequential(block: Sequential.()->Unit): Sequential {
//    val sequential = Sequential().apply(block)
//    sequences.add(sequential)
//    return sequential
//}
//
//fun Sequential.parallel(block: Parallel.() -> Unit): Stage {
//    val parallel = Parallel().apply(block)
//
//    stages.lastOrNull()?.let { stage ->
//        if(stage is Single){
//            parallel.buildTypes.forEach {
//                it.dependencies.snapshot(stage.buildType){}
//            }
//        }
//        if (stage is Parallel) {
//            throw IllegalStateException("paral lel cannot snapshot-depend on parallel")
//        }
//    }
//
//    stages.add(parallel)
//    return parallel
//}
//
//fun Sequential.build(bt: BuildType, block: BuildType.() -> Unit): BuildType {
//    bt.apply(block)
//
//    registerDependency(bt, stages)
//
//    stages.add(Single(bt))
//    return bt
//}
//
//private fun Sequential.registerDependency(bt: BuildType, stages: List<Stage>) {
//    stages.lastOrNull()?.let { stage ->
//        if(stage is Single) {
//            bt.dependencies.snapshot(stage.buildType){}
//        }
//        if (stage is Parallel) {
//            stage.buildTypes.forEach {
//                bt.dependencies.snapshot(it){}
//            }
//            stage.sequences.forEach {
//                registerDependency(bt, it.stages)
//            }
//        }
//    }
//}
//
//fun Project.sequential(block: Sequential.() -> Unit): Sequential {
//    val sequential = Sequential().apply(block)
//
//    //register all builds in pipeline
//    registerBuilds(sequential)
//
//    return sequential
//}
//
//private fun Project.registerBuilds(sequential: Sequential) {
//    sequential.stages.forEach {
//        if (it is Single) {
//            buildType(it.buildType)
//        }
//        if (it is Parallel) {
//            it.buildTypes.forEach {
//                buildType(it)
//            }
//            it.sequences.forEach {
//                registerBuilds(it)
//            }
//        }
//    }
//}
//
//fun BuildType.produces(artifacts: String) {
//    artifactRules = artifacts
//}
//
//
//fun BuildType.requires(bt: BuildType, artifacts: String) {
//    dependencies.artifacts(bt) {
//        artifactRules = artifacts
//    }
//}