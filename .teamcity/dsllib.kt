//import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
//import jetbrains.buildServer.configs.kotlin.v2018_2.Project
//import java.lang.IllegalStateException
//
//class Sequence {
//    val stages = arrayListOf<Stage>()
//}
//
//interface Stage
//
//class Single(val buildType: BuildType) : Stage
//
//class Parallel: Stage {
//    val buildTypes = arrayListOf<BuildType>()
//    val sequences = arrayListOf<Sequence>()
//}
//
//fun Parallel.build(bt: BuildType, block: BuildType.() -> Unit): BuildType {
//    bt.apply(block)
//    buildTypes.add(bt)
//    return bt
//}
//
//fun Parallel.sequence(block: Sequence.()->Unit): Sequence {
//    val sequence = Sequence().apply(block)
//    sequences.add(sequence)
//    return sequence
//}
//
//fun Sequence.parallel(block: Parallel.() -> Unit): Stage {
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
//fun Sequence.build(bt: BuildType, block: BuildType.() -> Unit): BuildType {
//    bt.apply(block)
//
//    registerDependency(bt, stages)
//
//    stages.add(Single(bt))
//    return bt
//}
//
//private fun Sequence.registerDependency(bt: BuildType, stages: List<Stage>) {
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
//fun Project.sequence(block: Sequence.() -> Unit): Sequence {
//    val sequence = Sequence().apply(block)
//
//    //register all builds in pipeline
//    registerBuilds(sequence)
//
//    return sequence
//}
//
//private fun Project.registerBuilds(sequence: Sequence) {
//    sequence.stages.forEach {
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