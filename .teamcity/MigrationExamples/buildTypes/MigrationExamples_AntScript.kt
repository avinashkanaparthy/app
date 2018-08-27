package MigrationExamples.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.ant

object MigrationExamples_AntScript : BuildType({
    name = "AntScript"

    steps {
        ant {
            mode = antScript {
                content = """
                    <project>
                    <target name ="deploy">
                     <echo message="Deployment logic goes here."/>
                     <echo message="Examples:"/>
                     <echo message=" - upload to FTP"/>
                     <echo message=" - perform SSH commands"/>
                     <echo message=" - trigger an external action"/>
                    </target>
                    </project>
                """.trimIndent()
            }
            targets = "deploy"
        }
    }
})
