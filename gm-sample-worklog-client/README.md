# gm-sample-worklog-client

This client provides a nice user interface so you don't have to use curl to see things

## Tech stack 

- [Linux Fedora](https://getfedora.org/pt_BR/workstation/)
- [Visual Studio Code](https://code.visualstudio.com/)
- [Node and npm](https://nodejs.org/)
- [Browserify](http://browserify.org/)
- [Budo](https://github.com/mattdesl/budo)
- [Vue](https://vuejs.org/)
- [VueRouter](https://router.vuejs.org/en/)
- [VueMaterial](https://vuematerial.io/)
- [Axios](https://github.com/axios/axios)
- [md5](https://www.npmjs.com/package/md5)
- [moment](https://momentjs.com/)
- [Muse-ui](http://www.muse-ui.org/#/install)

## Caveats 

- The vue-material just entered in beta phase and already switched the docs to this latest release. However the 
regular install still delivers the stable release. In order to get the accurate docs, put your browser to 
[this site](https://vue-material-old.netlify.com) instead of [this one](https://vuematerial.io).
- Stable vue-material lacks a decent datepicker. And beta vue-material lacks the entire layout engine. Therefore, we 
picked the calendar component from [Muse-ui](http://www.muse-ui.org/#/datePicker) and moved on.
- Since we have client and service fully decoupled, the 
[CORS](https://developer.mozilla.org/en-US/docs/Glossary/CORS) rules must take place. Spring Boot of course have 
[a way to fix this](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/CrossOrigin.html).

## How to execute this application

Having node and npm available from command line, all you need to do is to enter **gm-sample-worklog-client** and enter 
the following command:

```
npm install ; npm run dev
``` 

Then the default web browser will pop up for you.

This client will look for the service running at **localhost** port **8080**.

## How to test this application

Follow the very same steps needed to execute the app but change the console command to this: 

```
npm install ; npm run test
``` 

## How to evolve this application

