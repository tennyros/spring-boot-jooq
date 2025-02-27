# Spring-boot-JOOQ demo

![CI with Gradle](https://github.com/tennyros/spring-boot-jooq/workflows/CI%20Pipeline%20with%20Gradle/badge.svg)
![Coverage](https://github.com/tennyros/spring-boot-jooq/raw/coverage-badge/.github/badges/jacoco.svg)

## Prerequisites

- Gradle 8.11
- JDK 17

### After cloning the project you need to:

#### - make a copy of 'gradle.properties' (like 'cp gradle.properties.example gradle.properties') file and set your credentials according sample;

#### - specify in run/debug configurations in your IDEA for Main.java:
- VM options, for example: 
  - -Dspring.profiles.active=dev
- environment variables, like: 
  - DB_BASE=postgresql;
  - DB_NAME=your_db_name;пш
  - DB_PASSWORD=your_password;
  - DB_PORT=1234;
  - DB_SERVER=localhost;
  - DB_USERNAME=your_username;
  
### How to build:
- ./gradlew build;