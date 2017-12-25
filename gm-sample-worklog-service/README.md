# gm-sample-worklog-service

This service holds the API and the business logic of the project. 

## Programming Environment

The code was developed and tested on Linux [Fedora](https://getfedora.org/pt_BR/workstation/) 27 64 bits.
It uses openjdk 8 from fedora repos.
Visual Studio [Code](https://code.visualstudio.com/) (1.19.x) was the editor. 
[SDKMan!](http://sdkman.io/) was used to solve a few tooling needs.
[Gradle](https://gradle.org/) was chosen as build and dependency management tool instead maven.

## Caveats

Springboot gradle plugin only works on gradle 4.x series. If there is any need for older gradle versions, gradle 
wrapper shall be used.

The code plugin [vscode-java](https://github.com/redhat-developer/vscode-java) did not solved import and autocomplete 
correctly due a limitation about having top-level configuration files. Also just the build.gradle wasn't enough.

So, the eclipse plugin was added to build.gradle in order to generate .classpath and .project files. Then the plugin
started to work properly.

For unknown reason the vascode-java plugin was unable to find javax.persistence packages. However the application just 
ran fine. 

## Tech stack

- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Spring Data JPA](https://projects.spring.io/spring-data-jpa/)
- [H2 Database Engine](http://www.h2database.com/html/download.html)
- [FlyWay Schema Migrations](https://flywaydb.org/)

## How to execute this application

- Make sure you have **jdk 8** installed and available from command line.
- Install **'sdkman!'** and then use it to install **grade 4**.
- Clone locally the repository [https://github.com/sombriks/gm-sample-worklog](https://github.com/sombriks/gm-sample-worklog).
- Enter the folder containing this README and open a command line on it.
- Type **"gradle run"**.
- After download all dependencies it will start to listen on 8080 port.

## How to test this application

Follow all needed steps to exceute the application but type **"gradle test"** in the end.

## How to evolve this application

H2 database is quite neat to fast prototyping however it does not scales well on file mode. We might use it on server 
mode, or even replace it with Postgresql or Oracle.

Profile configurations were not set. A production-dedicated profile can se set. Something called 
application-prod.properties can be set and we choose the profile using a command-line argument.
