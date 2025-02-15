### After cloning the project you need to:

#### - make a copy of 'gradle.properties' (like cp liquibase.properties.example liquibase.properties) file and set your credentials according sample;

#### - specify in run/debug configurations in your IDEA for Main.java:
- VM options, for example: 
  - -Dspring.profiles.active=dev
- environment variables, like: 
  - DB_BASE=postgresql;
  - DB_NAME=your_db_name;
  - DB_PASSWORD=your_password;
  - DB_PORT=1234;
  - DB_SERVER=localhost;
  - DB_USERNAME=your_username;
  ##### (similar to MainTest.java, but for VM: -Dspring.profiles.active=test)
  
#### - create tables in the database by running command in root directory of the project:
- ./gradlew update;

#### - generate jooq classes by running the command in root directory of the project:
- ./gradlew generateJooq;

#### - carefully manage include blocks in changelog-master.xml files.