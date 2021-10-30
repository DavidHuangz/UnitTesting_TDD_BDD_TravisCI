# se754_assignment6

[![Build Status](https://travis-ci.com/Angstylemon/se754_assignment6.svg?token=h8YQ5s2xq5JU1VB27qfk&branch=main)](https://travis-ci.com/Angstylemon/se754_assignment6)

# Project Architecture #
This repository was setup as a Gradle project in IntelliJ. When running, ensure that all required dependencies have been downloaded.

## Server ##
The server consists of:
- A Java backend
- Java Springboot API controllers & services
- JSP Pages

To run the server, use `gradlew bootRun`.

## Testing ##
### Frontend ###
The frontend testing framework uses:
- Cucumber for scenario & step definitions
- Selenium for interaction with browser

To execute the tests, use `gradlew cucumber` with the server running.

Dependencies:
- SpringBoot
- Selenium
- Cucumber
- JSTL
- TomCat

### Backend ###
Backend tests are written in JUnit with Mockito. These are hooked into Travis-CI to ensure build integrity.

Dependencies:
- JUnit5
- Mockito
