import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.project
import jetbrains.buildServer.configs.kotlin.v2018_2.version

/*
   sequential    := (stage)+
   stage    := (serial|parallel)+
   parallel := (serial|sequential)+
   serial   := build
 */

version = "2018.2"

//val applicationJar = Compiler.artifact("application.jar")

project {

    val os = arrayListOf("Mac", "Win", "Lin")

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