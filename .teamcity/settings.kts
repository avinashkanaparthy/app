import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot


/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2018.2"

project {
    val chain = chain {
        build(Compile) {
            produce("application.jar")
        }
        parallel {
            build(Test1) {
                produce("test.reports.zip")
                requires(Compile, "application.jar")
            }
            build(Test2) {
                produce("test.reports.zip")
                requires(Compile, "application.jar")
            }
        }
        build(Package) {
            produce("application.zip")
            requires(Package, "application.jar")
        }
        build(Deploy) {
            requires(Package, "application.zip")
        }
    }
    println(chain)
}

object Compile : BuildType({
    name = "Compile"

})

object Test1 : BuildType({
    name = "Test1"

})

object Test2 : BuildType({
    name = "Test2"
    
})

object Package : BuildType({
    name = "Package"

})

object Deploy : BuildType({
    name = "Deploy"

})