# gm-sample-worklog

Sample solution to register and summarize work hours

## Features

- list all users
- users can:
  - register themselves
  - log their working hours
  - list their work hours given a time interval

## Non-functional requirements

- apply [CQRS](https://martinfowler.com/bliki/CQRS.html)
- any data caching solution
- any database write must be made by an authenticated user
- use [JWT](https://jwt.io/) for authentication
- java 8
- tests (unit and integration)

## NFR that might or might not be attended

- the ACID properties are optional (eventual consistency / NoSQL allowed)
- microservices with Docker
- kotlin instead java
- database schema migration system

## About this project structure

The root repository contains all needed projects to run the complete solution.
That way, a single checkout should bring a complete and functional system, since all modules share a single version.

- gm-sample-worklog-service
  - backend magic goes there
- gm-sample-worklog-client
  - a fancy client to interact with the service
