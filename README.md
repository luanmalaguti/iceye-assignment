# iceye-assignment

> Simple web app that displays offers two REST services:
* JSON object with the content of https://jsonplaceholder.typicode.com/posts
* Converts a given text to image for download 

## Endpoints

The project is running on Heroku: https://frozen-sea-17781.herokuapp.com/

Available endpoints

* **URL**

  `GET`  _/assignment_

  `GET`  _/ingest/{text}_ `required=[String]`
                             
### Project
Spring Boot-based project who provides REST services to be consumed by the client

* Requirements
  - Java 8
  - Maven
  
There are two option how to run:
```console
$ git https://github.com/luanmalaguti/iceye-assignment.git
$ cd iceye-assignment
$ java -jar iceye-assignment-1.0.0.jar
```
The application will be running on http://localhost:8080
