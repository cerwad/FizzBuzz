# FizzBuzz

## Purpose
This is a rest webserver which returns a list of strings on the endpoint /fizzbuzz/generate
This project serves three purposes:
- Testing the new Spring boot 3
- Showcase a few different ways of testing
- Technical test for Adevinta

## Features

### Main feature FizzBuzz

The original fizz-buzz consists in writing all numbers from 1 to 100 and just replacing all multiples of 3 by "fizz" and all multiples of 5 by "buzz"
This time you can decide wich multiple you want and which text you want. And also define the limit.
When accessing the url /fizzbuzz/generate you can provide 5 arguments :
- int1 first multiple
- text1 text to replace
- int2 second multiple
- text2 text to replace
- limit max number in the list

request exemple: http://localhost:8080/fizzbuzz/generate?int1=3&text1=Fizz&int2=5&text2=Buzz&limit=50

### Stats endpoint

When accessing the url GET /fizzbuzz/stats, the server will respond with a json document indicating the most used request
ex:
{
    "endpoint": "generate?int1=1&text1=text&int2=1&text2=text&limit=50",
    "hitNumber": 25
}

### Health check

Endpoint /actuator provides health stats of this server

### OpenApi definition

http://localhost:8080/swagger-ui.html
Webpage with the rest api documentation automatically generated from the code

## Features showcase

- TU using Spock and Groovy language
- TI using MockMvc
- Error handling following spec RFC 7807
- Spring boot 3 and Java 17
- Heartbeat
- OpenApi Specification
- Common logging and stat computing using Spring Interceptor
- Stat saving using async call