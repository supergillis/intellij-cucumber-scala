# intellij-cucumber-scala

This repository was forked from [https://github.com/vbmacher/intellij-cucumber-scala]().

There are some changes in this fork that allows Cucumber expressions to be parsed by IntelliJ IDEA by converting them to regular expressions.

## Building

    ./gradlew buildPlugin
    
## Installing

Go to the "Plugins" section in IntelliJ IDEA settings and click "Install Plugin from Disk". Choose the built JAR file `./cucumber-scala/build/libs/cucumber-scala-2019.1.3.jar`.
