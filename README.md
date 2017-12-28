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

## About this project structure

The root repository contains all needed projects to run the complete solution.
That way, a single checkout should bring a complete and functional system, since all modules share a single version.

- [gm-sample-worklog-service](gm-sample-worklog-service/README.md)
  - backend magic goes there
- [gm-sample-worklog-client](gm-sample-worklog-client/README.md)
  - a fancy client to interact with the service
