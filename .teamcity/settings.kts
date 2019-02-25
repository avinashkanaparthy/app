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

//val applicationJar = Compiler.artifact("application.jar")

project {

    val sequence = sequence {
        build(Compile) {
        }
        parallel {
            build(Test1) {
            }
            sequence {
                build(Test2) {
                }
                build(Test3) {
                }
                build(Test4) {
                }
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

