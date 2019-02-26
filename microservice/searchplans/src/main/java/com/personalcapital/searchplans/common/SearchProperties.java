package com.personalcapital.searchplans.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author love_taneja
 * This is a class to load custom Properties file from application.properties
 */
@ConfigurationProperties("app.search")
@Component
public class SearchProperties {

    private String elasticSearchHost;

    public String getElasticSearchHost() {
        return elasticSearchHost;
    }

    public void setElasticSearchHost(String elasticSearchHost) {
        this.elasticSearchHost = elasticSearchHost;
    }

}