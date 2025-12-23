plugins {
    id("java-library")
    id("org.allaymc.gradle.plugin") version "0.2.1"
}

group = "me.daoge.amoji"
description = "Show emoji in your allay server!"
version = "0.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

allay {
    api = "0.19.0"

    plugin {
        entrance = ".Amoji"
        authors += "daoge_cmd"
        website = "https://github.com/smartcmd/Amoji"
    }
}

dependencies {
    compileOnly(group = "org.projectlombok", name = "lombok", version = "1.18.34")
    annotationProcessor(group = "org.projectlombok", name = "lombok", version = "1.18.34")
}
