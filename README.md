# Phptravels
The program is part of the "Selenium Java from scratch course" on the Udemy platform, where the travel website is tested.
## Sources
1. [tutorialselenium](https://github.com/bartoszlagoda/tutorialselenium) - A project introducing the world of selenium java.
2. [Selenium Java course from scratch](https://www.udemy.com/course/kurs-selenium-java/) - The course on which this program was written.
3. [phptravels](http://www.kurs-selenium.pl/demo/) - Tested website.
## Table of contents
[General info](#general-info)
[Technologies](#technologies)
## General info
The presented repository is used to test the phptravels website as an extension of the "Selenium Java Basics Course" with the use of the Page Object Model, showing logs and the use of testNG.
## Technologies
* Java 18.0.1.1 2022-04-22
* Apache Maven 3.8.6
* Selenium 4.16.1
* TestNG 7.6.0
* Google Chrome 120.0.6099.200
* log4j 2.22.1
## Log4j
### Sources
* [Maven dependency](https://logging.apache.org/log4j/2.x/maven-artifacts.html)
### log4j support
#### Creating an object of the log4j class
```
private static final Logger logger = LogManager.getLogger();
```
#### Creating logs
```
logger.trace("Trace");
logger.debug("Debug");
logger.info("Info");
logger.warn("Warn");
logger.error("Error");
logger.fatal("Fatal");
```
#### Loggin levels
1. FATAL - show only logs for fatal
2. ERROR - show only logs for fatal and error
3. WARN - show only logs for fatal, error and warn
4. INFO - show only logs for fatal, error, warn and info
5. DEBUG - show only logs for fatal, error, warn, info and debug
6. TRACE - show all logs mentioned above


