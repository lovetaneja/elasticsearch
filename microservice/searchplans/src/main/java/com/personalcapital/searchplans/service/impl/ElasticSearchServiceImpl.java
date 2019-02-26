package com.personalcapital.searchplans.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.JsonParseException;
import com.personalcapital.searchplans.common.SearchConstants;
import com.personalcapital.searchplans.common.SearchProperties;
import com.personalcapital.searchplans.controller.SearchApiController;
import com.personalcapital.searchplans.dto.ElasticSearchResponseDTO;
import com.personalcapital.searchplans.exception.ApiException;
import com.personalcapital.searchplans.exception.ErrorCodes;
import org.apache.http.client.ClientProtocolException;
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
     * @throws ApiException
     */
    public List<ElasticSearchResponseDTO> searchPlans(Map<String, String> queryParams) throws ApiException {
        SearchResponse searchResponse = callAmazonElasticSearchService(queryParams);
        return parseResponse(searchResponse);
    }

    /**
     * @param queryParams
     * @return SearchResponse
     * @throws ApiException
     */
    private SearchResponse callAmazonElasticSearchService(Map<String, String> queryParams) throws ApiException {
        if (log.isDebugEnabled()) {
            log.debug("searchProperties.getElasticSearchHost() " + searchProperties.getElasticSearchHost());
        }
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(searchProperties.getElasticSearchHost())));
        SearchResponse response = null;
        try {
            response = client.search(buildSearchRequest(queryParams), RequestOptions.DEFAULT);
        } catch (ClientProtocolException clientProtocolException) {
            log.error("ClientProtocolException occurred while calling Amazon Elastic Search API ", clientProtocolException.getMessage(), clientProtocolException);
            throw new ApiException(ErrorCodes.REST_CALL_CLIENT_PROTOCOL_EXCEPTION_CODE, "IOException occurred while calling Amazon Elastic Search API");
        } catch (IOException ioException) {
            log.error("IOException occurred while calling Amazon Elastic Search API ", ioException.getMessage(), ioException);
            throw new ApiException(ErrorCodes.REST_CALL_IO_EXCEPTION_CODE, "IOException occurred while calling Amazon Elastic Search API");
        } catch (Exception exception) {
            log.error("Exception occured while calling Lifelock Native APIs:" + exception.getMessage(), exception);
            throw new ApiException(ErrorCodes.REST_CALL_EXCEPTION_CODE, "Exception occurred while calling Amazon Elastic Search API");
        }
        return response;
    }

    /**
     * @param queryParams
     * @return SearchRequest
     * @throws ApiException
     */
    private SearchRequest buildSearchRequest(Map<String, String> queryParams) throws ApiException {
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

    /**
     * @param searchResponse
     * @return
     * @throws ApiException
     */
    private List<ElasticSearchResponseDTO> parseResponse(SearchResponse searchResponse) throws ApiException {
        List<ElasticSearchResponseDTO> apiResponse = new ArrayList<>();
        if(searchResponse!=null){
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            for (SearchHit searchHit : searchHits) {
                if (searchHit!=null){
                    String source = searchHit.getSourceAsString();
                    Gson gson = new Gson();
                    ElasticSearchResponseDTO elasticSearchResponseDTO = null;
                    try {
                        elasticSearchResponseDTO = gson.fromJson(source, ElasticSearchResponseDTO.class);
                    } catch (JsonSyntaxException jsonSyntaxException) {
                        log.error("JsonSyntaxException occurred while parsing response of Amazon Elastic Search API ", jsonSyntaxException.getMessage(), jsonSyntaxException);
                        throw new ApiException(ErrorCodes.PARSE_RESPONSE_JSON_SYNTAX_EXCEPTION_CODE, "IOException occurred while calling Amazon Elastic Search API");
                    } catch (JsonIOException jsonIOException) {
                        log.error("JsonIOException occurred while parsing response of Amazon Elastic Search API ", jsonIOException.getMessage(), jsonIOException);
                        throw new ApiException(ErrorCodes.PARSE_RESPONSE_JSON_IO_EXCEPTION_CODE, "IOException occurred while calling Amazon Elastic Search API");
                    } catch (JsonParseException jsonParseException) {
                        log.error("JsonParseException occurred while parsing response of Amazon Elastic Search API ", jsonParseException.getMessage(), jsonParseException);
                        throw new ApiException(ErrorCodes.PARSE_RESPONSE_JSON_PARSE_EXCEPTION_CODE, "IOException occurred while calling Amazon Elastic Search API");
                    }
                    apiResponse.add(elasticSearchResponseDTO);
                }else{
                    log.error("searchHit is Null");
                    throw new ApiException(ErrorCodes.SEARCH_HIT_NULL_ERROR_CODE, "Error Response from Search API - searchHit is Null");
                }
            }
        }else{
            log.error("searchResponse is Null");
            throw new ApiException(ErrorCodes.SEARCH_RESPONSE_NULL_ERROR_CODE, "Error Response from Search API - searchResponse is Null");
        }
        return apiResponse;
    }
}