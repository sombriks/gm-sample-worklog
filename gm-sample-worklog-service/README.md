# gm-sample-worklog-service

This service holds the API and the business logic of the project. 

## Tech stack

- [Linux Fedora](https://getfedora.org/pt_BR/workstation/)
- [Visual Studio Code](https://code.visualstudio.com/)
- [SDKMan!](http://sdkman.io/)
- [Gradle](https://gradle.org/)
- [Lombok](https://projectlombok.org/)
- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Spring Data JPA](https://projects.spring.io/spring-data-jpa/)
- [H2 Database Engine](http://www.h2database.com/html/download.html)
- [FlyWay Schema Migrations](https://flywaydb.org/)
- [JUnit](http://junit.org/junit4/)
- [JaCoCo](http://www.jacoco.org/jacoco/)
- [Apache Commons Codec](https://commons.apache.org/proper/commons-codec/)
- [Postman](https://www.getpostman.com/)

## Caveats

- Spring Boot gradle plugin only works on gradle 4.x series.
- The code plugin [vscode-java](https://github.com/redhat-developer/vscode-java) did not solved import and 
autocomplete correctly due a limitation about having top-level configuration files instead to use all occurrences of 
these files. It was solved by using service folder as temporary project root.
- Also, the eclipse plugin was added to build.gradle in order to generate *.classpath* and *.project* files. Then the 
plugin started to work almost properly.
- ~~For unknown reason the vscode-java plugin was unable to find javax.persistence packages.~~ 
~~See [this bug](https://github.com/redhat-developer/vscode-java/issues/397) for further information.~~
Every time a new dependency is added, we need to run **gradle cleanEclipse ; gradle eclipse** again.
- In [Lombok setup](https://github.com/redhat-developer/vscode-java/wiki/Lombok-support) (to get autocomplete on @Data 
augmented classes for example) i had to hardcode the path to lombok jar inside **.vscode/settings.json**. You might 
need to edit this file in order to not explode your machine. 

## How to execute this application

- Make sure you have **jdk 8** installed and available from command line.
- Install **'sdkman!'** and then use it to install **grade 4**.
- Clone locally the repository [https://github.com/sombriks/gm-sample-worklog](https://github.com/sombriks/gm-sample-worklog).
- Enter the folder containing this README and open a command line on it.
- Type **"gradle run"**.
- After download all dependencies it will start to listen on 8080 port.

## How to test this application

Follow all needed steps to execute the application but type **"gradle test"** in the end.

Also, you can generate a coverage report by typing **"grade jacocoTestReport"**.

## How to evolve this application

H2 database is quite neat to fast prototyping however it does not scales well on file mode. We might use it on server 
mode, or even replace it with Postgresql or Oracle.

[Profile configurations](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-properties-and-configuration.html#howto-set-active-spring-profiles) were not set. A production-dedicated profile could optimize, for example, logging, but there's room for improvements.

Setup a continuous integration solution can help on test and deploy. workflow.

And speaking in workflow, [git-flow](http://danielkummer.github.io/git-flow-cheatsheet/) is our call for any team bigger than one.
 