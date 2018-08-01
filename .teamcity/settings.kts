import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

version = "2018.1"

project {

    params {
        text("text", "текст")
        password("passwordPhrase", "пароль")
        param("env.REPOSITORY_URL", "http://some.url")
    }
}

