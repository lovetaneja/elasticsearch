package com.personalcapital.searchplans.controller;

import com.personalcapital.searchplans.api.SearchApi;
import com.personalcapital.searchplans.common.SearchConstants;
import com.personalcapital.searchplans.dto.ElasticSearchResponseDTO;
import com.personalcapital.searchplans.exception.ApiException;
import com.personalcapital.searchplans.exception.ErrorCodes;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import com.personalcapital.searchplans.service.SearchService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author love_taneja
 * This is a controller class for searchPlans API
 */
@Controller
public class SearchApiController implements SearchApi {

    private static final Logger log = LogManager.getLogger(SearchApiController.class);

    @Autowired
    SearchService elasticSearchService;

    /**
     * @param query
     * @return ResponseEntity
     * @throws IOException
     * @throws ApiException
     */
    @Override
    public ResponseEntity<List<ElasticSearchResponseDTO>> searchPlans(@RequestParam(value = "query", required = false) String query) throws IOException, ApiException{
        List<ElasticSearchResponseDTO> searchPlansList = new ArrayList();
        if (log.isDebugEnabled()) {
            log.debug("query " + query);
        }
        if (!StringUtils.isEmpty(query)) {
            Map<String, String> queryParamsMap = getQueryParameters(query);
            if (log.isDebugEnabled()) {
                log.debug("queryParamsMap is :: " + queryParamsMap);
            }
            // validations for query parameters
            validateQueryParameters(queryParamsMap);

            searchPlansList = elasticSearchService.searchPlans(queryParamsMap);
        } else {
            throw new ApiException(ErrorCodes.VALIDATIONS_QUERY_PARAMETER_NULL_ERROR_CODE, "Error Response from Search API - Query Parameter is Null/Empty");
        }
        return new ResponseEntity<>(searchPlansList, HttpStatus.OK);
    }

    /**
     * @param query
     * @return Map of Query Parameters
     */
    private Map<String, String> getQueryParameters(String query) {
        Map<String, String> queryParamsMap = Arrays.stream(query.split(","))
                .map(s -> s.split(":"))
                .filter(a -> a.length == 2)
                .collect(Collectors.toMap(
                        a -> a[0],  //key
                        a -> a[1]  //value
                ));
        return queryParamsMap;
    }

    /**
     * @param queryParamsMap
     * @throws ApiException
     */
    private void validateQueryParameters(Map<String, String> queryParamsMap) throws ApiException{
        if (queryParamsMap.size() == 0) {
            throw new ApiException(ErrorCodes.VALIDATIONS_QUERY_PARAMETER_NOT_VALID_PATTERN_ERROR_CODE, "Error Response from Search API - Query Parameter Pattern is Not Valid");
        }
        if (!(queryParamsMap.containsKey(SearchConstants.QUERY_PARAM_PLAN_NAME)
                || queryParamsMap.containsKey(SearchConstants.QUERY_PARAM_SPONSOR_NAME)
                || queryParamsMap.containsKey(SearchConstants.QUERY_PARAM_SPONSOR_STATE))) {
            throw new ApiException(ErrorCodes.VALIDATIONS_QUERY_PARAMETER_NOT_VALID_NAME_ERROR_CODE, "Error Response from Search API - Query Parameter is Not Valid");
        }
        if (queryParamsMap.containsKey(SearchConstants.QUERY_PARAM_PLAN_NAME) && queryParamsMap.get(SearchConstants.QUERY_PARAM_PLAN_NAME).length() > 140) {
            throw new ApiException(ErrorCodes.VALIDATIONS_QUERY_PARAMETER_PLAN_NAME_SIZE_ERROR_CODE, "Error Response from Search API - Length of planName can't be more than 140");
        }
        if (queryParamsMap.containsKey(SearchConstants.QUERY_PARAM_SPONSOR_NAME) && queryParamsMap.get(SearchConstants.QUERY_PARAM_SPONSOR_NAME).length() > 70) {
            throw new ApiException(ErrorCodes.VALIDATIONS_QUERY_PARAMETER_SPONSOR_NAME_SIZE_ERROR_CODE, "Error Response from Search API - Length of sponsorName can't be more than 70");
        }
        if (queryParamsMap.containsKey(SearchConstants.QUERY_PARAM_SPONSOR_STATE) && queryParamsMap.get(SearchConstants.QUERY_PARAM_SPONSOR_STATE).length() > 2) {
            throw new ApiException(ErrorCodes.VALIDATIONS_QUERY_PARAMETER_SPONSOR_STATE_SIZE_ERROR_CODE, "Error Response from Search API - Length of sponsorState can't be more than 2");
        }
    }
}