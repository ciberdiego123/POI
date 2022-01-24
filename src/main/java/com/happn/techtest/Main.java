package com.happn.techtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.info("STARTING POI APPLICATION");
        SpringApplication.run(Main.class, args);
        LOGGER.info("POI APPLICATION FINISHED");
    }

}
