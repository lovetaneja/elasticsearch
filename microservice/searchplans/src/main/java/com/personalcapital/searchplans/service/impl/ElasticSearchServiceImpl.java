package com.personalcapital.searchplans.service.impl;

import com.personalcapital.searchplans.common.SearchConstants;
import com.personalcapital.searchplans.common.SearchProperties;
import com.personalcapital.searchplans.controller.SearchApiController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.personalcapital.searchplans.service.SearchService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.action.search.SearchRequest;
import java.io.IOException;

/**
 * @author love_taneja
 * This is a Service Class for Elastic Search Service for searchPlans API
 */
@Service("elasticSearchService")
public class ElasticSearchServiceImpl implements SearchService {

    private static final Logger log = LogManager.getLogger(SearchApiController.class);

    @Autowired
    protected SearchProperties searchProperties;

    /**
     * @param queryParams
     * @return List of Plans
     */
    public List<String> searchPlans(Map<String, String> queryParams){
        List<String> s = new ArrayList<>();
        s.add("love");
        s.add("taneja");
        if (log.isDebugEnabled()){
            log.debug("searchProperties.getElasticSearchHost() " + searchProperties.getElasticSearchHost());
        }
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(searchProperties.getElasticSearchHost())));

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(SearchConstants.INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("SPONSOR_DFE_NAME", "MECHANICAL SOLUTIONS1"));
        searchRequest.source(searchSourceBuilder);

        try{
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        }catch(IOException exc){
            log.error("IOException occurred while calling Amazon Elastic Search API ", exc);
        }
        return s;
    }
}