package com.personalcapital.searchplans.service.impl;

import com.google.gson.Gson;
import com.personalcapital.searchplans.common.SearchConstants;
import com.personalcapital.searchplans.common.SearchProperties;
import com.personalcapital.searchplans.controller.SearchApiController;
import com.personalcapital.searchplans.dto.ElasticSearchResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
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
    public List<ElasticSearchResponseDTO> searchPlans(Map<String, String> queryParams) {
        List<ElasticSearchResponseDTO> response = new ArrayList<>();
        if (log.isDebugEnabled()) {
            log.debug("searchProperties.getElasticSearchHost() " + searchProperties.getElasticSearchHost());
        }
        SearchResponse searchResponse = callAmazonElasticSearchService(queryParams);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for(SearchHit searchHit: searchHits){
            String source = searchHit.getSourceAsString();
            Gson gson = new Gson();
            ElasticSearchResponseDTO elasticSearchResponseDTO = gson.fromJson(source, ElasticSearchResponseDTO.class);
            response.add(elasticSearchResponseDTO);
        }
        return response;
    }

    /**
     * @param queryParams
     * @return SearchResponse
     */
    private SearchResponse callAmazonElasticSearchService(Map<String, String> queryParams) {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(searchProperties.getElasticSearchHost())));
        SearchResponse response = null;
        try {
            response = client.search(buildSearchRequest(queryParams), RequestOptions.DEFAULT);
        } catch (IOException exc) {
            log.error("IOException occurred while calling Amazon Elastic Search API ", exc);
        }
        return response;
    }

    /**
     * @param queryParams
     * @return SearchRequest
     */
    private SearchRequest buildSearchRequest(Map<String, String> queryParams) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(SearchConstants.INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        queryParams.forEach((k, v) -> {
            /* @TODO: There is scope to optimize this logic to make it more generic for future. For now, we have requirement to search by 3 fields only.*/
            if (SearchConstants.QUERY_PARAM_PLAN_NAME.equalsIgnoreCase(k)) {
                MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(SearchConstants.ELASTIC_SEARCH_INDEX_FIELD_PLAN_NAME, v);
                searchSourceBuilder.query(matchQueryBuilder);
            } else if (SearchConstants.QUERY_PARAM_SPONSOR_NAME.equalsIgnoreCase(k)) {
                MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(SearchConstants.ELASTIC_SEARCH_INDEX_FIELD_SPONSOR_NAME, v);
                searchSourceBuilder.query(matchQueryBuilder);
            } else if (SearchConstants.QUERY_PARAM_SPONSOR_STATE.equalsIgnoreCase(k)) {
                MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(SearchConstants.ELASTIC_SEARCH_INDEX_FIELD_SPONSOR_STATE, v);
                searchSourceBuilder.query(matchQueryBuilder);
            }
        });
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }
}