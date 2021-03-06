package com.personalcapital.searchplans.api;

import com.personalcapital.searchplans.dto.ElasticSearchResponseDTO;
import com.personalcapital.searchplans.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

/**
 * @author love_taneja
 * This is an interface for searchPlans API
 */
@FunctionalInterface
public interface SearchPlan {
    /**
     * @param query
     * @return ResponseEntity
     * @throws IOException
     * @throws ApiException
     */
    @RequestMapping(value = "/v1/plans", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<List<ElasticSearchResponseDTO>> searchPlans(@RequestParam(value = "query", required = false) String query) throws IOException, ApiException;
}