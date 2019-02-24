package com.personalcapital.searchplans;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author love_taneja
 * This is a Main Class for Spring Boot Application
 */
@SpringBootApplication
public class SearchApplication {

    private static final Logger log = LogManager.getLogger(SearchApplication.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
        log.info("----------------- Starting Search Application ---------------");
    }

}