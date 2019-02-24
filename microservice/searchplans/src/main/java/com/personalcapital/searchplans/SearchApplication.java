package com.personalcapital.searchplans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author love_taneja
 * This is a Main Class for Spring Boot Application
 */
@SpringBootApplication
public class SearchApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(SearchApplication.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
        log.info("----------------- Starting Search Application ---------------");
    }
}