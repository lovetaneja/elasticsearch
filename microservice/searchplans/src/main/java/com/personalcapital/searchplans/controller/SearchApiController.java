package com.personalcapital.searchplans.controller;

import com.personalcapital.searchplans.api.SearchApi;
import com.personalcapital.searchplans.dto.ElasticSearchResponseDTO;
import com.personalcapital.searchplans.exception.ApiException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import com.personalcapital.searchplans.service.SearchService;

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

    @Override
    public ResponseEntity<List<ElasticSearchResponseDTO>> searchPlans(@RequestParam(value = "query", required = false) String query) throws ApiException{
        List<ElasticSearchResponseDTO> searchPlansList = new ArrayList();
        if (log.isDebugEnabled()){
            log.debug("query " + query);
        }
        if (!StringUtils.isEmpty(query)){
            Map<String, String> queryParamsMap = getQueryParameters(query);
            if (log.isDebugEnabled()){
                log.debug("queryParamsMap is :: " + queryParamsMap);
            }
            // do validations for each query parameters
            searchPlansList = elasticSearchService.searchPlans(queryParamsMap);
        }else{
            return new ResponseEntity<>(searchPlansList, HttpStatus.BAD_REQUEST);
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
                .collect(Collectors.toMap(
                        a -> a[0],  //key
                        a -> a[1]   //value
                ));
        return queryParamsMap;
    }
}