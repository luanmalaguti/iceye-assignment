package com.iceye.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application executable that uses a Tomcat embedded servlet to provide a standalone application
 *
 * 	<p>How to execute?</p>
 * 	<p><code>java -jar beer-rest-service.jar</code></p>//TODO
 *
 * @author Luan Reffatti
 */
@SpringBootApplication
@ComponentScan()
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
