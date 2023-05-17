# Installation instructions

## What is it ?

It is a web server developed with java 17 and spring boot 3.
The code is packaged in an executable uber jar

## Requirements

Maven 3, JRE 17, port 8080 available

## Installation

To compile the project and generate the jar please execute the command :
mvn clean install

To run the application :
cd target
java -jar fizzbuzz-0.0.1-SNAPSHOT.jar

OR

mvn spring-boot:run