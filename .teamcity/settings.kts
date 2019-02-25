import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_2.Project
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.project
import jetbrains.buildServer.configs.kotlin.v2018_2.version
import java.lang.IllegalStateException

/*
   sequential    := (stage)+
   stage    := (serial|parallel)+
   parallel := (serial|sequential)+
   serial   := build
 */

version = "2018.2"

//val applicationJar = Compiler.artifact("application.jar")

project {

    val sequence = sequential {
        build(Compile) {
        }
        parallel {
            build(Test1) {
            }
            sequential {
                build(Test2) {
                }
//                parallel {
                    build(Test3) {
                    }
                    build(Test4) {
                    }
//                }
            }
        }
        build(Package) {
        }
        build(Deploy) {
        }
    }
    println(sequence)
}

object Compile : BuildType({
    name = "Compile"

    steps {
        script {
            scriptContent = "echo 'hello'"
        }
    }

})

object Test1 : BuildType({
    name = "Test1"

    steps {
        script {
            scriptContent = "touch test.reports.zip"
        }
    }

})

object Test2 : BuildType({
    name = "Test2"

    steps {
        script {
            scriptContent = "touch test.reports.zip"
        }
    }
})

object Test3 : BuildType({
    name = "Test3"

    steps {
        script {
            scriptContent = "touch test.reports.zip"
        }
    }
})

object Test4 : BuildType({
    name = "Test4"

})

object Package : BuildType({
    name = "Package"

    steps {
        script {
            scriptContent = "touch application.zip"
        }
    }
})

object Deploy : BuildType({
    name = "Deploy"

    type = Type.DEPLOYMENT
})


///----------------------
///----------------------
///----------------------

interface Stage

class Single(val buildType: BuildType) : Stage

class Parallel : Stage {
    val buildTypes = arrayListOf<BuildType>()
    val sequences = arrayListOf<Sequential>()
}

class Sequential : Stage {
    val stages = arrayListOf<Stage>()
}

fun Parallel.build(bt: BuildType, block: BuildType.() -> Unit): BuildType {
    bt.apply(block)
    buildTypes.add(bt)
    return bt
}

fun Parallel.sequential(block: Sequential.() -> Unit): Sequential {
    val sequence = Sequential().apply(block)
    sequences.add(sequence)
    return sequence
}

fun Sequential.parallel(block: Parallel.() -> Unit): Stage {
    val parallel = Parallel().apply(block)
    stages.add(parallel)
    return parallel
}

fun Sequential.build(bt: BuildType, block: BuildType.() -> Unit): BuildType {
    bt.apply(block)
    stages.add(Single(bt))
    return bt
}

fun Project.sequential(block: Sequential.() -> Unit): Sequential {
    val sequence = Sequential().apply(block)
    buildDependencies(sequence)
    registerBuilds(sequence)
    return sequence
}

fun buildDependencies(sequence: Sequential) {
    var prev: Stage? = null

    for (stage in sequence.stages) {
        if (prev != null) {
            if (prev is Single) {
                dependOnSingle(stage, prev)
            }
            if (prev is Parallel) {
                dependOnParallel(stage, prev)
            }
            if (prev is Sequential) {
                throw IllegalStateException("Sequential in sequential not supported")
            }
        }
        prev = stage
    }
}

fun dependOnSequence(stage: Stage, sequence: Sequential) {
    sequence.stages.lastOrNull()?.let {
        if (it is Single) {
            dependOnSingle(stage, it)
        }
        if (it is Parallel) {
            dependOnParallel(stage, it)
        }
        if (it is Sequential) {
            throw IllegalStateException("Sequential in sequential not supported")
        }
    }
}

fun dependOnParallel(stage: Stage, parallel: Parallel) {
    if (stage is Single) {
        parallel.buildTypes.forEach {
            stage.buildType.dependencies.snapshot(it) {}
        }
        parallel.sequences.forEach {
            dependOnSequence(stage, it)
        }
    }
    if (stage is Parallel) {
        throw IllegalStateException("Parallel cannot snapshot-depend on parallel")
    }
}

fun dependOnSingle(stage: Stage, single: Single) {
    if (stage is Single) {
        stage.buildType.dependencies.snapshot(single.buildType) {}
    }
    if (stage is Parallel) {
        stage.buildTypes.forEach {
            it.dependencies.snapshot(single.buildType) {}
        }
        stage.sequences.forEach {
            it.stages.firstOrNull()?.let { firstStage ->
                dependOnSingle(firstStage, single)
            }
        }
    }
}

fun Project.registerBuilds(sequence: Sequential) {
    sequence.stages.forEach {
        if (it is Single) {
            buildType(it.buildType)
        }
        if (it is Parallel) {
            it.buildTypes.forEach { build ->
                buildType(build)
            }
            it.sequences.forEach { seq ->
                registerBuilds(seq)
            }
        }
    }
}

fun BuildType.produces(artifacts: String) {}
fun BuildType.requires(bt: BuildType, artifacts: String) {}