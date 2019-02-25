import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.project
import jetbrains.buildServer.configs.kotlin.v2018_2.version

/*
   sequence    := (stage)+
   stage    := (serial|parallel)+
   parallel := (serial|sequence)+
   serial   := build
 */

version = "2018.2"

//Could do something like this to externalize artifacts from the pipeline
//val applicationJar = Compiler.artifact("application.jar")

project {

    val sequence = sequence {
        build(Compile) {
            produces("application.jar")
        }
        parallel {
            build(Test1) {
                requires(Compile, "application.jar")
                produces("test.reports.zip")
            }
            sequence {
                build(Test2) {
                    requires(Compile, "application.jar")
                    produces("test.reports.zip")
                }
                parallel {
                    build(Test3) {
                        requires(Compile, "application.jar")
                        produces("test.reports.zip")
                    }
                    build(Test4) {
                        requires(Compile, "application.jar")
                        produces("test.reports.zip")
                    }
                }
            }
        }
        build(Package) {
            requires(Compile, "application.jar")
            produces("application.zip")
        }
        build(Deploy) {
            requires(Package, "application.zip")
        }
    }
    println(sequence)
}

object Compile : BuildType({
    name = "Compile"

    steps {
        script {
            scriptContent = "touch application.jar"
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

    steps {
        script {
            scriptContent = "touch test.reports.zip"
        }
    }
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

