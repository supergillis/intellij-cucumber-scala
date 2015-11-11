intellij-cucumber-scala 0.3.2
=======================

Enables Reference tracking of glue code when using cucumber-scala DSL.

#Features

- [x] Navigate from feature step to step definition
- [ ] Find usages of step definitions in feature files
- [ ] Wizard that creates step definitions for a step in a feature file


#Development

1. `git clone git@github.com:danielwegener/intellij-cucumber-scala.git`
2. `sbt updateIdea` will download the idea sdk to the SDK folder and all required plugins
3. Import the project as sbt project into IDEA. Make sure to use the downloaded IDEA version (e.g. `$project_root/idea/143.371.42`) as plugin sdk.

Now you can build this plugin with `sbt package`

> IntelliJ Plugin Development is too hard :/ Please provide a public (maybe non-oss, non-free) repo for Idea artifacts. Or maybe a sbt plugin.

#License
This project is released under the __Apache License, Version 2.0__ (http://www.apache.org/licenses/LICENSE-2.0).
